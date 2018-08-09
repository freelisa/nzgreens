package com.nzgreens.common.service.impl;

import com.baomidou.mybatisplus.entity.Column;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
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
import org.apache.commons.lang3.StringUtils;
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
    @Autowired
    private OrderNumberUtils orderNumberUtils;

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
        List<Long> shoppingCartIdList = new ArrayList<>(shoppingCartList.size());
        Map<Long,Long> productNumberMap = new HashMap<>(shoppingCartList.size()*3/4);
        for (ShoppingCart shoppingCart : shoppingCartList) {
            shoppingCartIdList.add(shoppingCart.getId());
            productNumberMap.put(shoppingCart.getProductId(), shoppingCart.getProductNumber());
        }
        //商品无效
        if (CollectionUtils.isEmpty(productsList)) {
            //购物车全部删除
            if (!CollectionUtils.isEmpty(shoppingCartList)) {
                shoppingCartService.deleteBatchIds(shoppingCartIdList);
            }
            response.setSuccess(true);
            return response;
        }
        BaseResponse<GeneratorOrder> orderBaseResponse = this.order(productsList, productNumberMap ,user, deliveryModeEnum, addressId);
        response.setCode(orderBaseResponse.getCode());
        response.setSuccess(orderBaseResponse.isSuccess());
        response.setMsg(orderBaseResponse.getMsg());
        if (response.isSuccess()) {
            //更新用户购物车
            shoppingCartService.deleteBatchIds(shoppingCartIdList);
            this.renderSuccess(response, orderBaseResponse.getData());
        }
        return response;
    }


    @Transactional(rollbackFor = java.lang.Exception.class)
    @Override
    public BaseResponse<UserOrderDTO> generatorOrderTx(List<Long> productIdList, Map<Long, Long> productNumberMap, Users user,
                                                       DeliveryModeEnum deliveryModeEnum, Long addressId) throws Exception {
        BaseResponse<UserOrderDTO> response = new BaseResponse<>();
        List<Products> productsList = productsService.selectBatchIds(productIdList);
        BaseResponse<GeneratorOrder> orderBaseResponse = this.order(productsList, productNumberMap, user ,deliveryModeEnum, addressId);
        response.setCode(orderBaseResponse.getCode());
        response.setSuccess(orderBaseResponse.isSuccess());
        response.setMsg(orderBaseResponse.getMsg());
        if (response.isSuccess()) {
            this.renderSuccess(response, orderBaseResponse.getData());
        }
        return response;
    }

    /**
     * 商品下单流程
     * @param productNumberMap
     * @param user
     * @param deliveryModeEnum
     * @param addressId
     * @return
     * @throws Exception
     */
    private BaseResponse<GeneratorOrder> order (List<Products> productsList, Map<Long, Long> productNumberMap, Users user
            ,DeliveryModeEnum deliveryModeEnum, Long addressId) throws Exception{
        BaseResponse<GeneratorOrder> response = new BaseResponse<>();
        boolean continued = this.checkAndLockStock(productsList,productNumberMap);
        if (!continued) {
            response.setSuccess(false);
            response.setMsg("库存不足，无法下单");
            return response;
        }
        GeneratorOrder generatorOrder = new GeneratorOrder(user, deliveryModeEnum ,userAddressService.selectById(addressId),
                    productsList, productNumberMap).invoke();
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
        Date now = new Date();
        List<Long> productIdList = new ArrayList<>();
        List<Orders> agentOrderList = new ArrayList<>(ordersList.size());
        String orderNumber = orderNumberUtils.generatorOderNumber(agentUser.getId(), agentUser.getTelephone(), DeliveryModeEnum._SELF);
        Long productTotalPrice = 0L;
        Map<Long,Long> productNumberMap = new HashMap<>();
        for (Orders orders : ordersList) {
            productIdList.add(orders.getProductId());
            orders.setStatus(OrderStatusEnum._DONE.getStatus());
            orders.setUpdateTime(now);

            Orders agentOrder = new Orders();
            agentOrder.setOrderId(orders.getId());
            agentOrder.setOrderNumber(orderNumber);
            agentOrder.setProductId(orders.getProductId());
            agentOrder.setProductNumber(orders.getProductNumber());
            agentOrder.setPrice(orders.getAgentPrice());
            agentOrder.setAgentPrice(orders.getAgentPrice());
            agentOrder.setStatus(OrderStatusEnum._PENDING.getStatus());
            agentOrder.setCommentStatus(OrderCommentStatusEnum._NOT_COMMENTED.getStatus());
            agentOrderList.add(agentOrder);
            productTotalPrice += agentOrder.getPrice();

            productNumberMap.put(orders.getProductId(), orders.getProductNumber());
        }
        Long freight = this.getFreight(productIdList, DeliveryModeEnum._SELF, UserTypeEnum._AGENT, productNumberMap);
        //更新订单状态
        ordersService.updateBatchById(ordersList);
        //更新用户订单状态
        userOrder.setStatus(UserOrderStatusEnum._PROCESSED.getStatus());
        this.updateById(userOrder);

        //生成代理新订单、流水
        ordersService.insertBatch(agentOrderList);
        UserOrder agentOrder = this.generatorOrderAndLogs(agentUser, userBo.getSystemUser() , DeliveryModeEnum._SELF, productTotalPrice
                , freight , userAddress , orderNumber, userOrder.getOrderNumber());
        response.setSuccess(true);
        List<Long> orderIdList = new ArrayList<>();
        for (Orders orders : agentOrderList) {
            orderIdList.add(orders.getId());
        }
        UserOrderDTO userOrderDTO = new UserOrderDTO();
        userOrderDTO.setOrderId(orderIdList);
        userOrderDTO.setOrderNumber(orderNumber);
        response.setData(userOrderDTO);
        return response;
    }

    private Long getFreight(List<Long> productIdList, DeliveryModeEnum deliveryModeEnum , UserTypeEnum userTypeEnum,
                            Map<Long,Long> productNumberMap) {
        List<Products> productsList = productsService.selectList(new EntityWrapper<Products>()
                .setSqlSelect(Column.create().column(Products.WEIGHT),Column.create().column(Products.ID)).in(Products.ID, productIdList));
        return productUtils.computeFreight(this.getProductTotalWeight(productsList,productNumberMap),deliveryModeEnum,userTypeEnum);
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
        Map<Long,Long> productNumberMap = new HashMap<>(ordersList.size()*3/4);
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
        userLogs.setTriggerUserId(agentUser.getId());

        AccountLogs agentLogs = new AccountLogs();
        agentLogs.setUserId(agentUser.getId());
        agentLogs.setTriggerUserId(agentUser.getId());
        agentLogs.setRecordId(userOrder.getId());
        agentLogs.setBefore(agentUser.getBalance());
        agentLogs.setAmount(-userOrder.getPrice());
        agentLogs.setAfter(agentLogs.getBefore() + agentLogs.getAmount());
        agentLogs.setType(AccountLogsTypeEnum._ORDER_REFUSED.getType());

        agentUser.setBalance(agentLogs.getAfter());
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

    @Override
    public BaseResponse<UserOrderDTO> mergeOrderTx(Set<Long> orderIdSet,Set<String> orderNumberSet, Users agentUser, Long addressId) throws Exception {
        List<Orders> ordersList = ordersService.selectList(new EntityWrapper<Orders>()
                                        .in(Orders.ORDER_NUMBER,orderNumberSet)
                                        .eq(Orders.STATUS,OrderStatusEnum._PENDING.getStatus()));
        return getMergeUserOrderResponse(agentUser, addressId, ordersList);
    }

    @Override
    public BaseResponse<UserOrderDTO> mergeOrdersTx(List<Orders> ordersList, Users agentUser, Long addressId) throws Exception {
        return getMergeUserOrderResponse(agentUser, addressId, ordersList);
    }

    /**
     * 合并订单
     * @param agentUser
     * @param addressId
     * @param ordersList
     * @return
     * @throws Exception
     */
    public BaseResponse<UserOrderDTO> getMergeUserOrderResponse(Users agentUser, Long addressId, List<Orders> ordersList) throws Exception {
        BaseResponse<UserOrderDTO> response = new BaseResponse<>();
        Map<String,Integer> orderPendingMap = new HashMap<>();
        Date now = new Date();
        Set<Long> orderIdSet = new HashSet<>();
        ordersList.stream().forEach(orders -> orderIdSet.add(orders.getId()));
        List<Orders> agentOrderList = new ArrayList<>(orderIdSet.size());
        String orderNumber = orderNumberUtils.generatorOderNumber(agentUser.getId(),agentUser.getTelephone(), DeliveryModeEnum._SELF);
        Long productTotalPrice = 0L;
        for (Orders orders : ordersList) {
            Integer count = orderPendingMap.get(orders.getOrderNumber());
            if (count == null) {
                count = 0;
            }
            count += 1;
            orderPendingMap.put(orders.getOrderNumber(), count);
        }
        List<Orders> mergeOrderList = ordersService.selectList(new EntityWrapper<Orders>()
                        .in(Orders.ID,orderIdSet)
                        .eq(Orders.STATUS, OrderStatusEnum._PENDING.getStatus()));
        Map<String,Integer> mergePendingMap = new HashMap<>();
        List<Long> productIdList = new ArrayList<>();
        Map<Long,Long> productNumberMap = new HashMap<>();
        Long originalProductTotalPrice = 0L;
        Map<Long,Long> agentOrderMap = new HashMap<>();
        for (Orders orders : mergeOrderList) {
            Integer count = mergePendingMap.get(orders.getOrderNumber());
            if (count == null) {
                count = 0;
            }
            count += 1;
            mergePendingMap.put(orders.getOrderNumber(), count);

            orders.setStatus(OrderStatusEnum._DONE.getStatus());
            orders.setUpdateTime(now);

            Orders agentOrder = new Orders();
            agentOrder.setOrderId(orders.getId());
            agentOrder.setOrderNumber(orderNumber);
            agentOrder.setProductId(orders.getProductId());
            agentOrder.setProductNumber(orders.getProductNumber());
            agentOrder.setPrice(orders.getAgentPrice());
            agentOrder.setAgentPrice(orders.getAgentPrice());
            agentOrder.setStatus(OrderStatusEnum._PENDING.getStatus());
            agentOrder.setCommentStatus(OrderCommentStatusEnum._NOT_COMMENTED.getStatus());
            agentOrderList.add(agentOrder);
            //代理自身合并下单订单
            if (Long.valueOf(0).equals(orders.getAgentPrice()) || orders.getAgentPrice() == null ) {
                agentOrderMap.put(orders.getId(), orders.getPrice());
                originalProductTotalPrice += orders.getPrice();
            }

            productTotalPrice += agentOrder.getPrice();
            productIdList.add(orders.getProductId());

            productNumberMap.put(orders.getProductId(), orders.getProductNumber());
        }
        List<String> orderNumberList = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : mergePendingMap.entrySet()) {
            if (orderPendingMap.get(entry.getKey()).equals(entry.getValue())) {
                orderNumberList.add(entry.getKey());
            }
        }
        long freight = this.getFreight(productIdList, DeliveryModeEnum._SELF, UserTypeEnum._AGENT,productNumberMap);
        //更新用户订单
        ordersService.updateBatchById(mergeOrderList);
        ordersService.insertBatch(agentOrderList);
        String userOrderNumbers = null;
        if (!CollectionUtils.isEmpty(orderNumberList)) {
            userOrderNumbers = StringUtils.join(orderNumberList,",");
            List<UserOrder> userOrderList = this.selectList(new EntityWrapper<UserOrder>().in(UserOrder.ORDER_NUMBER, orderNumberList));
            for (UserOrder userOrder : userOrderList) {
                userOrder.setStatus(UserOrderStatusEnum._PROCESSED.getStatus());
            }
            this.updateBatchById(userOrderList);
        }
        //生成订单使用代理价格（除去了代理自己合并下单的价钱）
        UserOrder userOrder = this.generatorOrderAndLogs(agentUser, userBo.getSystemUser(), DeliveryModeEnum._SELF, productTotalPrice
                                , freight, userAddressService.selectById(addressId), orderNumber, userOrderNumbers);
        //总价需加上代理合并下单的价钱，为了拒绝时退款。
        userOrder.setPrice(userOrder.getPrice() + originalProductTotalPrice);
        userOrder.setProductPrice(userOrder.getProductPrice() + originalProductTotalPrice);
        this.updateById(userOrder);
        response.setSuccess(true);
        List<Long> orderIdList = new ArrayList<>();
        for (Orders orders : agentOrderList) {
            orderIdList.add(orders.getId());
            if (agentOrderMap.containsKey(orders.getOrderId())) {
                orders.setPrice(agentOrderMap.get(orders.getOrderId()));
            }
        }
        ordersService.updateBatchById(agentOrderList);
        UserOrderDTO userOrderDTO = new UserOrderDTO();
        userOrderDTO.setOrderId(orderIdList);
        userOrderDTO.setOrderNumber(orderNumber);
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
                                         Long freight, String orderNumber, Long productTotalPrice ,String userOrderNumber) {
        UserOrder userOrder = new UserOrder();
        //用户
        userOrder.setUserId(user.getId());
        if (userOrderNumber != null) {
            userOrder.setUserOrderNumber(userOrderNumber);
        }
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
    private Long getProductTotalWeight(List<Products> productsList,Map<Long,Long> productNumberMap) {
        Long totalWeight = 0L;
        for (Products products : productsList) {
            totalWeight += products.getWeight() == null ? 0 : products.getWeight()*productNumberMap.get(products.getId());
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

    private void renderSuccess(BaseResponse<UserOrderDTO> response, GeneratorOrder generatorOrder) {
        String orderNumber = generatorOrder.getOrderNumber();
        List<Orders> ordersList = generatorOrder.getOrdersList();
        UserOrderDTO userOrderDTO = new UserOrderDTO();
        userOrderDTO.setOrderNumber(orderNumber);
        userOrderDTO.setOrderId(this.getOrderIdList(ordersList));
        response.setSuccess(true);
        response.setData(userOrderDTO);
    }


    /**
     * 生成用户订单，更新用户流水
     * @param user
     * @param higherUser
     * @param deliveryModeEnum
     * @param productTotalPrice
     * @param freight
     * @param userAddress
     * @param orderNumber
     * @return
     * @throws Exception
     */
    private UserOrder generatorOrderAndLogs(Users user, Users higherUser, DeliveryModeEnum deliveryModeEnum
            , Long productTotalPrice , Long freight, UserAddress userAddress , String orderNumber, String userOrderNumber) throws Exception {
        //生成用户订单
        UserOrder userOrder = this.generatorUserOrder(user, deliveryModeEnum, userAddress,
                freight, orderNumber, productTotalPrice, userOrderNumber);
        //生成账户流水
        AccountLogs userLogs = new AccountLogs();
        userLogs.setUserId(user.getId());
        userLogs.setTriggerUserId(user.getId());
        userLogs.setBefore(user.getBalance());
        userLogs.setAmount(-userOrder.getPrice());
        userLogs.setAfter(userLogs.getBefore() + userLogs.getAmount());
        userLogs.setType(AccountLogsTypeEnum._ORDER.getType());
        //用户余额
        user.setBalance(userLogs.getAfter());
        //TODO  注释余额下单比大于0
/*        if (user.getBalance() < 0) {
            throw new Exception("金币不足，无法下单！");
        }*/
        //上级流水
        AccountLogs higherLogs = new AccountLogs();
        higherLogs.setUserId(higherUser.getId());
        higherLogs.setTriggerUserId(user.getId());
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
        //设置订单号
        user.setLastOrderNumber(Long.valueOf(orderNumber));
        usersList.add(user);
        usersList.add(higherUser);

        //数据库操作开始

        //更新用户账户
        usersService.updateBatchById(usersList);
        this.insert(userOrder);
        //新增用户流水
        userLogs.setRecordId(userOrder.getId());
        higherLogs.setRecordId(userOrder.getId());
        accountLogsService.insertBatch(accountLogsList);
        return userOrder;
    }





    private class GeneratorOrder {
        private Users user;
        private DeliveryModeEnum deliveryModeEnum;
        private UserAddress userAddress;
        private List<Products> productsList;
        private Map<Long, Long> productNumberMap;
        private String orderNumber;
        private List<Orders> ordersList;
        private UserOrder userOrder;

        public GeneratorOrder(Users user, DeliveryModeEnum deliveryModeEnum, UserAddress userAddress
                , List<Products> productsList, Map<Long, Long> productNumberMap) {
            this.user = user;
            this.deliveryModeEnum = deliveryModeEnum;
            this.userAddress = userAddress;
            this.productsList = productsList;
            this.productNumberMap = productNumberMap;
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
            Map<Long,Long> productPriceMap = new HashMap<>(productsList.size()*3/4);
            Map<Long,Long> productRealPriceMap = new HashMap<>(productsList.size()*3/4);
            Long totalWeight = 0L;
            List<Long> productIdList = new ArrayList<>();
            for (Products products : productsList) {
                productPriceMap.put(products.getId(), products.getSellingPrice());
                productRealPriceMap.put(products.getId(), products.getSellingPrice());
                productIdList.add(products.getId());
                totalWeight += products.getWeight() == null ? 0 : products.getWeight()*productNumberMap.get(products.getId());
            }
            user = usersService.selectById(user.getId());
            //上级用户
            Users higherUser = null;
            //普通用户查询代理设置商品价格
            if (!UserTypeEnum.isAgent(user)) {
                UserAgent userAgent = userAgentBo.getUserAgent(user.getId());
                List<ProductPrice> productPriceList = this.searchProductPrice(userAgent, productIdList);
                this.setProductRealPriceMap(productPriceList,productRealPriceMap);
                //上级用户为代理
                higherUser = usersService.selectById(userAgent.getAgentUserId());
            } else {
                //代理下单系统用户
                higherUser = userBo.getSystemUser();
            }
            orderNumber = orderNumberUtils.generatorOderNumber(user.getId(), user.getTelephone(), deliveryModeEnum);
            //生成商品对应订单
            ordersList = new ArrayList<>(productsList.size());
            Long productTotalPrice = 0L;
            for (Long productId : productIdList) {
                Orders orders = new Orders();
                orders.setOrderNumber(orderNumber);
                orders.setProductId(productId);
                orders.setProductNumber(productNumberMap.get(productId));
                orders.setPrice(productRealPriceMap.get(productId) * productNumberMap.get(productId));
                if (UserTypeEnum.isAgent(user) && DeliveryModeEnum._DELIVERY.equals(deliveryModeEnum)) {
                    orders.setAgentPrice(0L);
                } else {
                    orders.setAgentPrice(productPriceMap.get(productId) * productNumberMap.get(productId));
                }
                orders.setStatus(OrderStatusEnum._PENDING.getStatus());
                orders.setCommentStatus(OrderCommentStatusEnum._NOT_COMMENTED.getStatus());
                ordersList.add(orders);
                productTotalPrice += orders.getPrice();
            }
            Long freight = productUtils.computeFreight(totalWeight, deliveryModeEnum, UserTypeEnum.getUserTypeEnum(user));
            //新增用户订单
            ordersService.insertBatch(ordersList);
            userOrder = generatorOrderAndLogs(user, higherUser, deliveryModeEnum , productTotalPrice
                    ,freight, userAddress, orderNumber ,null);
            return this;
        }

        /**
         * 获取商品真实售价
         * @param productPriceList
         * @return
         */
        private void setProductRealPriceMap(List<ProductPrice> productPriceList, Map<Long,Long> realMap){
            for (ProductPrice productPrice : productPriceList) {
                realMap.put(productPrice.getProductId(), productPrice.getSellingPrice());
                if (productPrice.getAgentPrice() != null) {
                    realMap.put(productPrice.getProductId(), productPrice.getAgentPrice());
                }
            }
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
    }
}
