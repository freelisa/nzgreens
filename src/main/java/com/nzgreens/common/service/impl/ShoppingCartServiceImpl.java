package com.nzgreens.common.service.impl;

import com.nzgreens.common.entity.ShoppingCart;
import com.nzgreens.common.mapper.ShoppingCartMapper;
import com.nzgreens.common.service.ShoppingCartService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 购物车 服务实现类
 * </p>
 *
 * @author sylar
 * @since 2018-04-05
 */
@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {
	
}
