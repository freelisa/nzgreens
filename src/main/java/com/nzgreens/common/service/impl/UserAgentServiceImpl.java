package com.nzgreens.common.service.impl;

import com.nzgreens.common.entity.UserAgent;
import com.nzgreens.common.mapper.UserAgentMapper;
import com.nzgreens.common.service.UserAgentService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户代理关联表 服务实现类
 * </p>
 *
 * @author sylar
 * @since 2018-04-05
 */
@Service
public class UserAgentServiceImpl extends ServiceImpl<UserAgentMapper, UserAgent> implements UserAgentService {
	
}
