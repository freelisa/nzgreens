package com.nzgreens.common.service.impl;

import com.nzgreens.common.bo.UserAgentBo;
import com.nzgreens.common.bo.UserBo;
import com.nzgreens.common.common.enums.*;
import com.nzgreens.common.common.result.BaseResponse;
import com.nzgreens.common.common.utils.OrderNumberUtils;
import com.nzgreens.common.common.utils.ProductUtils;
import com.nzgreens.common.entity.*;
import com.nzgreens.common.entity.extend.UserOrderDTO;
import com.nzgreens.common.entity.service.ProductPrice;
import com.nzgreens.common.entity.service.UserProductPriceSearch;
import com.nzgreens.common.mapper.ProductsMapper;
import com.nzgreens.common.mapper.UserOrderMapper;
import com.nzgreens.common.service.*;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户订单关联表 服务实现类
 * </p>
 *
 * @author sylar
 * @since 2018-04-05
 */
@Service
public class UserOrderServiceImpl extends ServiceImpl<UserOrderMapper, UserOrder> implements UserOrderService {
    @Autowired
    private ProductUtils productUtils;
    @Autowired
    private ProductsService productsService;
    @Autowired
    private ProductsMapper productsMapper;
    @Autowired
    private UserAgentBo userAgentBo;
    @Autowired
    private AccountLogsService accountLogsService;
    @Autowired
    private UserBo userBo;
    @Autowired
    private UsersService usersService;
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private OrdersService ordersService;
    /**
     * 1.获取商品列表
     * 2.商品重量计算每个订单的金额
     * 3.订单总金额
     * 4.下架商品
     * 5.下架购物车
     * 6.扣除金币
     * 7.生成订单
     * 8.生成流水
     * @param shoppingCartList
     * @param user
     * @param deliveryModeEnum
     * @param addressId
     * @return
     */
    @Transactional(rollbackFor = java.lang.Exception.class)
    @Override
    public BaseResponse<UserOrderDTO> generatorOrderTx(List<ShoppingCart> shoppingCartList, Users user,
                                         DeliveryModeEnum deliveryModeEnum, Long addressId) throws Exception{
        BaseResponse<UserOrderDTO> response = new BaseResponse<>();
        List<Long> productIdList = this.getProductList(shoppingCartList);
        List<Products> productsList = productsService.selectBatchIds(productIdList);
        //商品无效，购物车全部删除
        if (CollectionUtils.isEmpty(productsList)) {
            shoppingCartService.deleteBatchIds(this.getShoppingCartList(shoppingCartList));
            response.setSuccess(true);
            return response;
        }
        Map<Long,Long> productNumberMap = this.getProductNumber(shoppingCartList);
        boolean continued = this.checkAndLockStock(productsList,productNumberMap);
        if (!continued) {
            response.setSuccess(false);
            response.setMsg("库存不足，无法下单");
            return response;
        }
        Map<Long,Long> productPriceMap = new HashMap<>(productIdList.size()*3/4);
        Map<Long,Long> productWeightMap = new HashMap<>(productIdList.size()*3/4);
        Map<Long,Long> productRealPriceMap = new HashMap<>(productIdList.size()*3/4);
        this.setProductPriceAndWeight(productsList, productPriceMap, productRealPriceMap, productWeightMap);

        UserAgent userAgent = null;
        if (!UserTypeEnum.isAgent(user)) {
            userAgent = userAgentBo.getUserAgent(user.getId());
            List<ProductPrice> productPriceList = this.searchProductPrice(userAgent, productIdList);
            this.setProductPriceMap(productPriceList,productPriceMap,productRealPriceMap);
        }
        String orderNumber = OrderNumberUtils.generatorOderNumber(user.getId(), deliveryModeEnum);
        //生成商品对应订单
        List<Orders> ordersList = new ArrayList<>(productIdList.size());
        Long productTotalPrice = 0L;
        for (Long productId : productIdList) {
            Orders orders = new Orders();
            orders.setOrderNumber(orderNumber);
            orders.setProductId(productId);
            orders.setProductNumber(productNumberMap.get(productId));
            orders.setPrice(productPriceMap.get(productId) * productNumberMap.get(productId));
            //自收小订单直接已处理，代收/合并默认未合并
            orders.setStatus(DeliveryModeEnum._SELF.equals(deliveryModeEnum) ?
                    OrderStatusEnum._DONE.getStatus() : OrderStatusEnum._PENDING.getStatus());
            orders.setCommentStatus(OrderCommentStatusEnum._NOT_COMMENTED.getStatus());
            ordersList.add(orders);
            productTotalPrice += orders.getPrice();
        }
        //生成用户订单
        UserOrder userOrder = this.generatorUserOrder(user, deliveryModeEnum, addressId,
                                    productUtils.computeFreight(this.getProductTotalWeight(productsList)),
                                    orderNumber, productTotalPrice);

        //生成账户流水
        AccountLogs userLogs = new AccountLogs();
        userLogs.setUserId(user.getId());
        userLogs.setBefore(user.getBalance());
        userLogs.setAmount(-userOrder.getPrice());
        userLogs.setAfter(userLogs.getBefore() + userLogs.getAmount());
        userLogs.setType(AccountLogsTypeEnum._ORDER.getType());
        //用户余额
        user.setBalance(userLogs.getAfter());
        if (user.getBalance() < 0) {
           throw new Exception("金币不足，无法下单！");
        }
        //上级流水
        Users higherUser = null;
        //代理下单系统用户
        if (UserTypeEnum.isAgent(user)) {
            higherUser = userBo.getSystemUser();
        //普通用户代理
        } else {
            higherUser = usersService.selectById(userAgent.getAgentUserId());
        }
        AccountLogs higherLogs = new AccountLogs();
        higherLogs.setUserId(higherUser.getId());
        higherLogs.setBefore(higherUser.getBalance());
        higherLogs.setAmount(userOrder.getPrice());
        higherLogs.setAfter(higherLogs.getBefore() + higherLogs.getAmount());
        higherLogs.setType(AccountLogsTypeEnum._ORDER.getType());
        //上级余额
        higherUser.setBalance(higherLogs.getAfter());

        List<AccountLogs> accountLogsList = new ArrayList<>();
        accountLogsList.add(userLogs);
        accountLogsList.add(higherLogs);

        List<Users> usersList = new ArrayList<>();
        usersList.add(user);
        usersList.add(higherUser);

        //数据库操作开始
        productsService.updateBatchById(productsList);
        //更新用户账户
        usersService.updateBatchById(usersList);
        //更新用户购物车
        shoppingCartService.deleteBatchIds(this.getShoppingCartList(shoppingCartList));
        //新增用户订单
        ordersService.insertBatch(ordersList);
        this.insert(userOrder);
        //新增用户流水
        userLogs.setRecordId(userOrder.getId());
        higherLogs.setRecordId(userOrder.getId());
        accountLogsService.insertBatch(accountLogsList);
        UserOrderDTO userOrderDTO = new UserOrderDTO();
        userOrderDTO.setOrderNumber(orderNumber);
        userOrderDTO.setOrderId(this.getOrderIdList(ordersList));
        response.setSuccess(true);
        response.setData(userOrderDTO);
        return response;
    }

    /**
     * 检查库存锁库存
     * @param productsList
     * @param productNumberMap
     * @return
     */
    private boolean checkAndLockStock(List<Products> productsList, Map<Long, Long> productNumberMap) {
        for (Products products : productsList) {
            products.setStock(products.getStock() - productNumberMap.get(products.getId()));
            if (products.getStock() < 0) {
                return false;
            }
        }
        return productsService.updateBatchById(productsList);
    }

    private List<Long> getOrderIdList(List<Orders> ordersList) {
        List<Long> orderIdList = new ArrayList<>(ordersList.size());
        for (Orders orders : ordersList) {
            orderIdList.add(orders.getId());
        }
        return orderIdList;
    }

    private void setProductPriceAndWeight(List<Products> productsList, Map<Long, Long> productPriceMap,
                                          Map<Long, Long> productRealPriceMap, Map<Long, Long> productWeightMap) {
        for (Products products : productsList) {
            productPriceMap.put(products.getId(), products.getSellingPrice());
            productRealPriceMap.put(products.getId(), products.getSellingPrice());
            productWeightMap.put(products.getId(), products.getWeight());
        }
    }

    private UserOrder generatorUserOrder(Users user, DeliveryModeEnum deliveryModeEnum, Long addressId,
                                         Long freight, String orderNumber, Long productTotalPrice) {
        UserOrder userOrder = new UserOrder();
        userOrder.setUserId(user.getId());
        //订单号
        userOrder.setOrderNumber(orderNumber);
        //运费
        userOrder.setFreight(freight);
        //商品总价
        userOrder.setProductPrice(productTotalPrice);
        //订单总价
        userOrder.setPrice(userOrder.getFreight() + userOrder.getProductPrice());
        //用户订单状态
        userOrder.setStatus(UserOrderStatusEnum._PENDING.getStatus());
        //代理订单状态
        userOrder.setAgentStatus(UserOrderAgentStatusEnum._PENDING.getStatus());
        userOrder.setType((UserTypeEnum.isAgent(user) && DeliveryModeEnum._SELF.equals(deliveryModeEnum)) ?
                UserOrderTypeEnum._SYSTEM.getType() : UserOrderTypeEnum._AGENT.getType());
        userOrder.setDeliveryMode(deliveryModeEnum.getType());
        userOrder.setAddressId(addressId);
        return userOrder;
    }

    private Long getProductTotalWeight(List<Products> productsList) {
        Long totalWeight = 0L;
        for (Products products : productsList) {
            totalWeight += products.getWeight() == null ? 0 : products.getWeight();
        }
        return totalWeight;
    }

    private List<Long> getProductList(List<ShoppingCart> shoppingCartList){
        List<Long> productList = new ArrayList<>(shoppingCartList.size());
        for (ShoppingCart shoppingCart : shoppingCartList) {
            productList.add(shoppingCart.getProductId());
        }
        return productList;
    }
    private List<Long> getShoppingCartList(List<ShoppingCart> shoppingCartList){
        List<Long> productList = new ArrayList<>(shoppingCartList.size());
        for (ShoppingCart shoppingCart : shoppingCartList) {
            productList.add(shoppingCart.getId());
        }
        return productList;
    }

    private Map<Long,Long> getProductNumber(List<ShoppingCart> shoppingCartList){
        Map<Long,Long> productNumberMap = new HashMap<>(shoppingCartList.size()*3/4);
        for (ShoppingCart shoppingCart : shoppingCartList) {
            productNumberMap.put(shoppingCart.getProductId(), shoppingCart.getProductNumber());
        }
        return productNumberMap;
    }
    /**
     * 获取商品价格
     * @param userAgent
     * @param productIdList
     * @return
     */
    private List<ProductPrice> searchProductPrice(UserAgent userAgent, List<Long> productIdList){
        UserProductPriceSearch priceSearch = new UserProductPriceSearch();
        priceSearch.setAgentUserId(userAgent.getAgentUserId());
        priceSearch.setProductIdList(productIdList);
        return productsMapper.selectUserProductPrice(priceSearch);
    }

    /**
     * 获取商品售价
     * @param productPriceList
     * @return
     */
    private void setProductPriceMap(List<ProductPrice> productPriceList,Map<Long,Long> sellingMap, Map<Long,Long> realMap){
        for (ProductPrice productPrice : productPriceList) {
            realMap.put(productPrice.getProductId(), productPrice.getSellingPrice());

            if (productPrice.getAgentPrice() != null) {
                sellingMap.put(productPrice.getProductId(), productPrice.getAgentPrice());
                continue;
            }
            sellingMap.put(productPrice.getProductId(), productPrice.getSellingPrice());
        }
    }
}
