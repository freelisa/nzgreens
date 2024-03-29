package com.nzgreens.common.bo;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.nzgreens.common.common.enums.UserTypeEnum;
import com.nzgreens.common.entity.Users;
import com.nzgreens.common.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by sylar on 2018/4/21.
 */
@Component
public class UserBo {
    @Autowired
    private UsersService usersService;
    @Autowired
    private UserAgentBo userAgentBo;
    /**
     * 获取系统用户
     * @return
     */
    public Users getSystemUser(){
        return usersService.selectOne(new EntityWrapper<Users>().eq(Users.TYPE , UserTypeEnum._SYSTEM.getType()));
    }

    /**
     * 获取用户代理用户
     * @param userId
     * @return
     */
    public Users getAgentUser(Long userId){
        return usersService.selectById(userAgentBo.getUserAgent(userId).getAgentUserId());
    }
}
