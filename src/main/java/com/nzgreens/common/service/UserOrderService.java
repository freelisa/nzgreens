package com.nzgreens.common.service;

import com.nzgreens.common.common.enums.DeliveryModeEnum;
import com.nzgreens.common.common.result.BaseResponse;
import com.nzgreens.common.entity.ShoppingCart;
import com.nzgreens.common.entity.UserOrder;
import com.baomidou.mybatisplus.service.IService;
import com.nzgreens.common.entity.Users;
import com.nzgreens.common.entity.extend.UserOrderDTO;

import java.util.List;
import java.util.Map;

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
     * @exception
     * @return
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
     */
    BaseResponse<UserOrderDTO> generatorOrderTx(List<Long> productIdList, Map<Long, Long> productNumberMap, Users user,
                                                DeliveryModeEnum deliveryModeEnum, Long addressId) throws Exception;

    /**
     * 审核通过订单
     * @param userOrder
     * @param agentUser
     * @exception
     * @return
     */
    BaseResponse<UserOrderDTO> auditPassOrderTx(UserOrder userOrder, Users agentUser) throws Exception;

    /**
     * 拒绝用户订单
     * @param userOrder
     * @throws Exception
     */
    void deleteUserOrdersTx(UserOrder userOrder)throws Exception;
}
