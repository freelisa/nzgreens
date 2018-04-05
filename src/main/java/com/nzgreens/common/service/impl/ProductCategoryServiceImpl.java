package com.nzgreens.common.service.impl;

import com.nzgreens.common.entity.ProductCategory;
import com.nzgreens.common.mapper.ProductCategoryMapper;
import com.nzgreens.common.service.ProductCategoryService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品分类表 服务实现类
 * </p>
 *
 * @author sylar
 * @since 2018-04-05
 */
@Service
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory> implements ProductCategoryService {
	
}
