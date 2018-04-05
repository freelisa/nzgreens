package com.nzgreens.common.service.impl;

import com.nzgreens.common.entity.Products;
import com.nzgreens.common.mapper.ProductsMapper;
import com.nzgreens.common.service.ProductsService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author sylar
 * @since 2018-04-05
 */
@Service
public class ProductsServiceImpl extends ServiceImpl<ProductsMapper, Products> implements ProductsService {
	
}
