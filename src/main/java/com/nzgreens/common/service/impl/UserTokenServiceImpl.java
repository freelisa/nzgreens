package com.nzgreens.common.service.impl;

import com.nzgreens.common.entity.UserToken;
import com.nzgreens.common.mapper.UserTokenMapper;
import com.nzgreens.common.service.UserTokenService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户token表 服务实现类
 * </p>
 *
 * @author sylar
 * @since 2018-04-05
 */
@Service
public class UserTokenServiceImpl extends ServiceImpl<UserTokenMapper, UserToken> implements UserTokenService {
	
}
