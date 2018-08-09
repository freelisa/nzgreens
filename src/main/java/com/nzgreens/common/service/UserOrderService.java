package com.nzgreens.common.service;

import com.nzgreens.common.common.enums.DeliveryModeEnum;
import com.nzgreens.common.common.result.BaseResponse;
import com.nzgreens.common.entity.Orders;
import com.nzgreens.common.entity.ShoppingCart;
import com.nzgreens.common.entity.UserOrder;
import com.baomidou.mybatisplus.service.IService;
import com.nzgreens.common.entity.Users;
import com.nzgreens.common.entity.extend.UserOrderDTO;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 用户订单关联表 服务类
 * </p>
 *
 * @author sylar
 * @since 2018-04-05
 */
public interface UserOrderService extends IService<UserOrder> {

    /**
     * 用户购物车下单生成订单
     * @param shoppingCartList
     * @param user
     * @param deliveryModeEnum
     * @param addressId
     * @return
     * @throws Exception
     */
    BaseResponse<UserOrderDTO> generatorOrderTx(List<ShoppingCart> shoppingCartList, Users user,
                                                 DeliveryModeEnum deliveryModeEnum, Long addressId) throws Exception;

    /**
     * 商品下单生成订单
     * @param productIdList
     * @param productNumberMap
     * @param user
     * @param deliveryModeEnum
     * @param addressId
     * @return
     * @throws Exception
     */
    BaseResponse<UserOrderDTO> generatorOrderTx(List<Long> productIdList, Map<Long, Long> productNumberMap, Users user,
                                                DeliveryModeEnum deliveryModeEnum, Long addressId) throws Exception;


    /**
     * 审核通过订单
     * @param userOrder
     * @param agentUser
     * @return
     * @throws Exception
     */
    BaseResponse<UserOrderDTO> auditPassOrderTx(UserOrder userOrder, Users agentUser) throws Exception;

    /**
     * 拒绝用户订单
     * @param userOrder
     * @throws Exception
     */
    void deleteUserOrdersTx(UserOrder userOrder)throws Exception;


    /**
     * 合并订单下单
     * @param orderIdList
     * @param orderNumberSet
     * @param user
     * @param addressId
     * @return
     * @throws Exception
     */
    @Deprecated
    BaseResponse<UserOrderDTO> mergeOrderTx(Set<Long> orderIdList, Set<String> orderNumberSet, Users agentUser, Long addressId) throws Exception;


    /**
     * 合并订单下单
     * @param ordersList
     * @param user
     * @param addressId
     * @return
     * @throws Exception
     */
    BaseResponse<UserOrderDTO> mergeOrdersTx(List<Orders> ordersList, Users agentUser, Long addressId) throws Exception;
}
