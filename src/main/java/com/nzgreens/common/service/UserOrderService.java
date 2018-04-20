package com.nzgreens.common.service;

import com.nzgreens.common.entity.ShoppingCart;
import com.nzgreens.common.entity.UserOrder;
import com.baomidou.mybatisplus.service.IService;
import com.nzgreens.common.entity.Users;
import com.nzgreens.common.entity.extend.UserOrderDTO;

import java.util.List;

/**
 * <p>
 * 用户订单关联表 服务类
 * </p>
 *
 * @author sylar
 * @since 2018-04-05
 */
public interface UserOrderService extends IService<UserOrder> {

    UserOrderDTO generatorOrder(List<ShoppingCart> shoppingCartList, Users user);
}
