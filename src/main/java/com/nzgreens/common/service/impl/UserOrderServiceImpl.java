package com.nzgreens.common.service.impl;

import com.nzgreens.common.entity.UserOrder;
import com.nzgreens.common.mapper.UserOrderMapper;
import com.nzgreens.common.service.UserOrderService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
	
}
