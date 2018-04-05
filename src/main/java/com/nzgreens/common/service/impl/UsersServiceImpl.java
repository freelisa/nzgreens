package com.nzgreens.common.service.impl;

import com.nzgreens.common.entity.Users;
import com.nzgreens.common.mapper.UsersMapper;
import com.nzgreens.common.service.UsersService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author sylar
 * @since 2018-04-05
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {
	
}
