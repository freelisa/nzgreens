package com.nzgreens.common.service.impl;

import com.nzgreens.common.entity.UserAddress;
import com.nzgreens.common.mapper.UserAddressMapper;
import com.nzgreens.common.service.UserAddressService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户收货地址 服务实现类
 * </p>
 *
 * @author sylar
 * @since 2018-04-05
 */
@Service
public class UserAddressServiceImpl extends ServiceImpl<UserAddressMapper, UserAddress> implements UserAddressService {
	
}
