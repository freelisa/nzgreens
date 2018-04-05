package com.nzgreens.common.service.impl;

import com.nzgreens.common.entity.AgentProducts;
import com.nzgreens.common.mapper.AgentProductsMapper;
import com.nzgreens.common.service.AgentProductsService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 代理商品价格表 服务实现类
 * </p>
 *
 * @author sylar
 * @since 2018-04-05
 */
@Service
public class AgentProductsServiceImpl extends ServiceImpl<AgentProductsMapper, AgentProducts> implements AgentProductsService {
	
}
