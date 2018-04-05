package com.nzgreens.common.service.impl;

import com.nzgreens.common.entity.ProductComment;
import com.nzgreens.common.mapper.ProductCommentMapper;
import com.nzgreens.common.service.ProductCommentService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品评价表 服务实现类
 * </p>
 *
 * @author sylar
 * @since 2018-04-05
 */
@Service
public class ProductCommentServiceImpl extends ServiceImpl<ProductCommentMapper, ProductComment> implements ProductCommentService {
	
}
