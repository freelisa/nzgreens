package com.nzgreens.common.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.nzgreens.common.bo.UserAgentBo;
import com.nzgreens.common.bo.UserBo;
import com.nzgreens.common.common.enums.*;
import com.nzgreens.common.common.result.BaseResponse;
import com.nzgreens.common.common.utils.OrderNumberUtils;
import com.nzgreens.common.common.utils.ProductUtils;
import com.nzgreens.common.entity.*;
import com.nzgreens.common.entity.extend.BaseOrderItem;
import com.nzgreens.common.entity.extend.UserOrderDTO;
import com.nzgreens.common.entity.service.ProductPrice;
import com.nzgreens.common.entity.service.UserProductPriceSearch;
import com.nzgreens.common.mapper.ProductsMapper;
import com.nzgreens.common.mapper.UserOrderMapper;
import com.nzgreens.common.service.*;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

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
    @Autowired
    private UserAddressService userAddressService;

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
        Map<Long,Long> productNumberMap = this.getProductNumber(shoppingCartList);
        BaseResponse<GeneratorOrder> orderBaseResponse = this.order(productIdList, productNumberMap ,user
                , shoppingCartList, deliveryModeEnum, addressId);
        response.setCode(orderBaseResponse.getCode());
        response.setSuccess(orderBaseResponse.isSuccess());
        response.setMsg(orderBaseResponse.getMsg());
        if (response.isSuccess()) {
            //更新用户购物车
            shoppingCartService.deleteBatchIds(UserOrderServiceImpl.this.getShoppingCartList(shoppingCartList));
            this.renderSuccess(response, orderBaseResponse.getData());
        }
        return response;
    }


    @Transactional(rollbackFor = java.lang.Exception.class)
    @Override
    public BaseResponse<UserOrderDTO> generatorOrderTx(List<Long> productIdList, Map<Long, Long> productNumberMap, Users user,
                                                       DeliveryModeEnum deliveryModeEnum, Long addressId) throws Exception {
        BaseResponse<UserOrderDTO> response = new BaseResponse<>();
        BaseResponse<GeneratorOrder> orderBaseResponse = this.order(productIdList, productNumberMap, user
                ,null,deliveryModeEnum, addressId);
        response.setCode(orderBaseResponse.getCode());
        response.setSuccess(orderBaseResponse.isSuccess());
        response.setMsg(orderBaseResponse.getMsg());
        if (response.isSuccess()) {
            this.renderSuccess(response, orderBaseResponse.getData());
        }
        return response;
    }

    private BaseResponse<GeneratorOrder> order (List<Long> productIdList, Map<Long, Long> productNumberMap, Users user
            , List<ShoppingCart> shoppingCartList, DeliveryModeEnum deliveryModeEnum, Long addressId) throws Exception{
        BaseResponse<GeneratorOrder> response = new BaseResponse<>();
        List<Products> productsList = productsService.selectBatchIds(productIdList);
        //商品无效
        if (CollectionUtils.isEmpty(productsList)) {
            //购物车全部删除
            if (!CollectionUtils.isEmpty(shoppingCartList)) {
                shoppingCartService.deleteBatchIds(this.getShoppingCartList(shoppingCartList));
            }
            response.setSuccess(true);
            return response;
        }
        boolean continued = this.checkAndLockStock(productsList,productNumberMap);
        if (!continued) {
            response.setSuccess(false);
            response.setMsg("库存不足，无法下单");
            return response;
        }
        GeneratorOrder generatorOrder = new GeneratorOrder(user, deliveryModeEnum,userAddressService.selectById(addressId),
                productIdList, productsList, productNumberMap).invoke();
        response.setSuccess(true);
        response.setData(generatorOrder);
        return response;
    }

    /**
     * 通过订单
     * 1.更新订单
     * 2.更新用户订单
     * 3.生成代理订单
     * @param userOrder
     */
    @Override
    public BaseResponse<UserOrderDTO> auditPassOrderTx(UserOrder userOrder,Users agentUser) throws Exception {
        BaseResponse<UserOrderDTO> response = new BaseResponse<>();
        UserAddress userAddress = new UserAddress();
        userAddress.setAddress(userOrder.getAddress());
        userAddress.setContact(userOrder.getContact());
        userAddress.setTelephone(userOrder.getTelephone());
        List<Orders> ordersList = ordersService.selectList(new EntityWrapper<Orders>().eq(Orders.ORDER_NUMBER, userOrder.getOrderNumber()));
        if (CollectionUtils.isEmpty(ordersList)) {
            response.setSuccess(true);
            return response;
        }
        List<Long> productIdList = new ArrayList<>();
        Map<Long,Long> productNumberMap = new HashMap<>();
        Map<Long,Long> productOrderMap = new HashMap<>();
        Date now = new Date();
        for (Orders orders : ordersList) {
            productNumberMap.put(orders.getProductId(),orders.getProductNumber());
            productOrderMap.put(orders.getProductId(),orders.getId());
            productIdList.add(orders.getProductId());
            orders.setStatus(OrderStatusEnum._DONE.getStatus());
            orders.setUpdateTime(now);
        }
        //生成代理新订单、流水
        GeneratorOrder generatorOrder = new GeneratorOrder(agentUser, DeliveryModeEnum._SELF, userAddress, productIdList,
                productsService.selectBatchIds(productIdList), productNumberMap ,productOrderMap).invoke();
        //更新订单状态
        ordersService.updateBatchById(ordersList);
        //更新用户订单状态
        userOrder.setStatus(UserOrderStatusEnum._PROCESSED.getStatus());
        this.updateById(userOrder);
        this.renderSuccess(response, generatorOrder);
        return response;
    }

    @Override
    public void deleteUserOrdersTx(UserOrder userOrder) throws Exception {
        List<Orders> ordersList = ordersService.selectList(new EntityWrapper<Orders>()
                .eq(Orders.ORDER_NUMBER, userOrder.getOrderNumber()));
        if (CollectionUtils.isEmpty(ordersList)) {
            return;
        }
        Date now = new Date();
        List<Long> productIdList = new ArrayList<>(ordersList.size());
        Map<Long,Long> productNumberMap = new HashMap<>();
        for (Orders orders : ordersList) {
            orders.setStatus(OrderStatusEnum._DONE.getStatus());
            orders.setUpdateTime(now);
            productIdList.add(orders.getProductId());
            productNumberMap.put(orders.getProductId(), orders.getProductNumber());
        }
        userOrder.setStatus(UserOrderStatusEnum._REFUSED.getStatus());
        List<Products> productsList = productsService.selectBatchIds(productIdList);

        Users users = usersService.selectById(userOrder.getUserId());
        AccountLogs userLogs = new AccountLogs();
        userLogs.setUserId(users.getId());
        userLogs.setRecordId(userOrder.getId());
        userLogs.setBefore(users.getBalance());
        userLogs.setAmount(userOrder.getPrice());
        userLogs.setAfter(userLogs.getBefore() + userLogs.getAmount());
        userLogs.setType(AccountLogsTypeEnum._ORDER_REFUSED.getType());

        users.setBalance(userLogs.getAfter());

        Users agentUser = userBo.getAgentUser(users.getId());
        AccountLogs agentLogs = new AccountLogs();
        agentLogs.setUserId(agentUser.getId());
        agentLogs.setRecordId(userOrder.getId());
        agentLogs.setBefore(agentUser.getBalance());
        agentLogs.setAmount(-userOrder.getPrice());
        agentLogs.setAfter(agentLogs.getBefore() + agentLogs.getAmount());
        agentLogs.setType(AccountLogsTypeEnum._ORDER_REFUSED.getType());

        agentUser.setBalance(agentLogs.getAfter());
        if (agentUser.getBalance() < 0) {
            throw new Exception("金币不足，无法拒绝该订单！");
        }
        List<AccountLogs> accountLogsList = new ArrayList<>(2);
        accountLogsList.add(userLogs);
        accountLogsList.add(agentLogs);
        List<Users> usersList = new ArrayList<>(2);
        usersList.add(users);
        usersList.add(agentUser);

        if (!CollectionUtils.isEmpty(productsList)) {
            for (Products products : productsList) {
                products.setStock(products.getStock() + productNumberMap.getOrDefault(products.getId() ,0L));
            }
            productsService.updateBatchById(productsList);
        }
        //更新数据
        usersService.updateBatchById(usersList);
        updateById(userOrder);
        ordersService.updateBatchById(ordersList);
        accountLogsService.insertBatch(accountLogsList);
    }

    /**
     * 检查库存锁库存
     * @param productsList
     * @param productNumberMap
     * @return
     */
    private boolean checkAndLockStock(List<Products> productsList, Map<Long, Long> productNumberMap) {
        for (Products products : productsList) {
            if (products.getStock() == null) {
                return false;
            }
            products.setStock(products.getStock() - productNumberMap.get(products.getId()));
            if (products.getStock() < 0) {
                return false;
            }
        }
        return productsService.updateBatchById(productsList);
    }

    /**
     * 获取订单ID列表
     * @param ordersList
     * @return
     */
    private List<Long> getOrderIdList(List<Orders> ordersList) {
        List<Long> orderIdList = new ArrayList<>(ordersList.size());
        for (Orders orders : ordersList) {
            orderIdList.add(orders.getId());
        }
        return orderIdList;
    }

    /**
     * 设置产品价格，售价，重量
     * @param productsList
     * @param productPriceMap
     * @param productRealPriceMap
     * @param productWeightMap
     */
    private void setProductPriceAndWeight(List<Products> productsList, Map<Long, Long> productPriceMap,
                                          Map<Long, Long> productRealPriceMap, Map<Long, Long> productWeightMap) {
        for (Products products : productsList) {
            productPriceMap.put(products.getId(), products.getSellingPrice());
            productRealPriceMap.put(products.getId(), products.getSellingPrice());
            productWeightMap.put(products.getId(), products.getWeight());
        }
    }

    /**
     * 生成订单实体类
     * @param user
     * @param deliveryModeEnum
     * @param userAddress
     * @param freight
     * @param orderNumber
     * @param productTotalPrice
     * @return
     */
    private UserOrder generatorUserOrder(Users user, DeliveryModeEnum deliveryModeEnum, UserAddress userAddress,
                                         Long freight, String orderNumber, Long productTotalPrice) {
        UserOrder userOrder = new UserOrder();
        //用户
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
        //订单处理类型
        userOrder.setType((UserTypeEnum.isAgent(user) && DeliveryModeEnum._SELF.equals(deliveryModeEnum)) ?
                UserOrderTypeEnum._SYSTEM.getType() : UserOrderTypeEnum._AGENT.getType());
        //收货方式
        userOrder.setDeliveryMode(deliveryModeEnum.getType());
        //收货地址
        userOrder.setAddress(userAddress != null ? userAddress.getAddress() : "");
        userOrder.setContact(userAddress != null ? userAddress.getContact() : "");
        userOrder.setTelephone(userAddress != null ? userAddress.getTelephone() : "");
        return userOrder;
    }

    /**
     * 获取商品总重量
     * @param productsList
     * @return
     */
    private Long getProductTotalWeight(List<Products> productsList) {
        Long totalWeight = 0L;
        for (Products products : productsList) {
            totalWeight += products.getWeight() == null ? 0 : products.getWeight();
        }
        return totalWeight;
    }

    /**
     * 获取商品ID列表
     * @param shoppingCartList
     * @return
     */
    private List<Long> getProductList(List<ShoppingCart> shoppingCartList){
        List<Long> productList = new ArrayList<>(shoppingCartList.size());
        for (ShoppingCart shoppingCart : shoppingCartList) {
            productList.add(shoppingCart.getProductId());
        }
        return productList;
    }

    /**
     * 获取购物车ID列表
     * @param shoppingCartList
     * @return
     */
    private List<Long> getShoppingCartList(List<ShoppingCart> shoppingCartList){
        List<Long> productList = new ArrayList<>(shoppingCartList.size());
        for (ShoppingCart shoppingCart : shoppingCartList) {
            productList.add(shoppingCart.getId());
        }
        return productList;
    }

    /**
     * 获取商品数量MAP
     * @param shoppingCartList
     * @return
     */
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
     * 获取商品真实售价
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

    private void renderSuccess(BaseResponse<UserOrderDTO> response, GeneratorOrder generatorOrder) {
        String orderNumber = generatorOrder.getOrderNumber();
        List<Orders> ordersList = generatorOrder.getOrdersList();
        UserOrderDTO userOrderDTO = new UserOrderDTO();
        userOrderDTO.setOrderNumber(orderNumber);
        userOrderDTO.setOrderId(this.getOrderIdList(ordersList));
        response.setSuccess(true);
        response.setData(userOrderDTO);
    }
    private class GeneratorOrder {
        private Users user;
        private DeliveryModeEnum deliveryModeEnum;
        private UserAddress userAddress;
        private List<Long> productIdList;
        private List<Products> productsList;
        private Map<Long, Long> productNumberMap;
        private String orderNumber;
        private List<Orders> ordersList;
        private Map<Long,Long> productOrderMap;
        private UserOrder userOrder;

        public GeneratorOrder(Users user, DeliveryModeEnum deliveryModeEnum, UserAddress userAddress,
                              List<Long> productIdList, List<Products> productsList, Map<Long, Long> productNumberMap) {
            this.user = user;
            this.deliveryModeEnum = deliveryModeEnum;
            this.userAddress = userAddress;
            this.productIdList = productIdList;
            this.productsList = productsList;
            this.productNumberMap = productNumberMap;
        }
        public GeneratorOrder(Users user, DeliveryModeEnum deliveryModeEnum, UserAddress userAddress, List<Long> productIdList,
                              List<Products> productsList, Map<Long, Long> productNumberMap, Map<Long, Long> productOrderMap) {
            this.user = user;
            this.deliveryModeEnum = deliveryModeEnum;
            this.userAddress = userAddress;
            this.productIdList = productIdList;
            this.productsList = productsList;
            this.productNumberMap = productNumberMap;
            this.productOrderMap = productOrderMap;
        }
        public String getOrderNumber() {
            return orderNumber;
        }

        public List<Orders> getOrdersList() {
            return ordersList;
        }

        public UserOrder getUserOrder() {
            return userOrder;
        }

        public GeneratorOrder invoke() throws Exception {
            Map<Long,Long> productPriceMap = new HashMap<>(productIdList.size()*3/4);
            Map<Long,Long> productWeightMap = new HashMap<>(productIdList.size()*3/4);
            Map<Long,Long> productRealPriceMap = new HashMap<>(productIdList.size()*3/4);
            UserOrderServiceImpl.this.setProductPriceAndWeight(productsList, productPriceMap, productRealPriceMap, productWeightMap);
            user = usersService.selectById(user.getId());
            //代理用户
            UserAgent userAgent = null;
            //上级用户
            Users higherUser = null;
            //普通用户查询代理设置商品价格
            if (!UserTypeEnum.isAgent(user)) {
                userAgent = userAgentBo.getUserAgent(user.getId());
                List<ProductPrice> productPriceList = UserOrderServiceImpl.this.searchProductPrice(userAgent, productIdList);
                UserOrderServiceImpl.this.setProductPriceMap(productPriceList,productPriceMap,productRealPriceMap);
                //上级用户为代理
                higherUser = usersService.selectById(userAgent.getAgentUserId());
            } else {
                //代理下单系统用户
                higherUser = userBo.getSystemUser();
            }
            orderNumber = OrderNumberUtils.generatorOderNumber(user.getId(), deliveryModeEnum);
            //生成商品对应订单
            ordersList = new ArrayList<>(productIdList.size());
            Long productTotalPrice = 0L;
            for (Long productId : productIdList) {
                Orders orders = new Orders();
                orders.setOrderNumber(orderNumber);
                orders.setProductId(productId);
                orders.setProductNumber(productNumberMap.get(productId));
                orders.setPrice(productPriceMap.get(productId) * productNumberMap.get(productId));
                orders.setStatus(OrderStatusEnum._PENDING.getStatus());
                orders.setCommentStatus(OrderCommentStatusEnum._NOT_COMMENTED.getStatus());
                if (!CollectionUtils.isEmpty(productOrderMap)) {
                    orders.setOrderId(productOrderMap.get(productId));
                }
                ordersList.add(orders);
                productTotalPrice += orders.getPrice();
            }

            //生成用户订单
            userOrder = UserOrderServiceImpl.this.generatorUserOrder(user, deliveryModeEnum, userAddress,
                                        productUtils.computeFreight(UserOrderServiceImpl.this.getProductTotalWeight(productsList)),
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

            //更新用户账户
            usersService.updateBatchById(usersList);
            //新增用户订单
            ordersService.insertBatch(ordersList);
            UserOrderServiceImpl.this.insert(userOrder);
            //新增用户流水
            userLogs.setRecordId(userOrder.getId());
            higherLogs.setRecordId(userOrder.getId());
            accountLogsService.insertBatch(accountLogsList);
/*            //普通用户新增代理返佣记录
            if (!UserTypeEnum.isAgent(user)) {
                AgentRebate agentRebate = agentRebateService.selectOne(new EntityWrapper<AgentRebate>()
                        .eq(AgentRebate.AGENT_USER_ID, userAgent.getAgentUserId()));
                if (agentRebate != null) {
                    AgentRebateAudit agentRebateAudit = new AgentRebateAudit();
                    agentRebateAudit.setAgentUserId(userAgent.getAgentUserId());
                    agentRebateAudit.setUserOrderId(userOrder.getId());
                    agentRebateAudit.setActualRebatePrice(userOrder.getProductPrice() * agentRebate.getOrderRebate() / 100);
                    agentRebateAudit.setRebatePrice(userOrder.getProductPrice() * agentRebate.getOrderRebate() / 100);
                    agentRebateAudit.setStatus(AgentRebateAuditStatusEnum._PENDING.getType());
                    agentRebateAudit.setType(AgentRebateTypeEnum._ORDER.getType());
                    agentRebateAuditService.insert(agentRebateAudit);
                }
            }*/
            return this;
        }
    }
}
