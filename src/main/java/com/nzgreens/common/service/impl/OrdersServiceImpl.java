package com.nzgreens.common.service.impl;

import com.nzgreens.common.entity.Orders;
import com.nzgreens.common.mapper.OrdersMapper;
import com.nzgreens.common.service.OrdersService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author sylar
 * @since 2018-04-05
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {
	
}
