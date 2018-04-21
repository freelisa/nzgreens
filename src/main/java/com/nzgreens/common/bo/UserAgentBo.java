package com.nzgreens.common.bo;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.nzgreens.common.entity.UserAgent;
import com.nzgreens.common.service.UserAgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by sylar on 2018/4/14.
 */
@Component
public class UserAgentBo {
    @Autowired
    private UserAgentService userAgentService;

    /**
     * 获取用户代理关系
     * @param userId
     * @return
     */
    public UserAgent getUserAgent(Long userId){
        return userAgentService.selectOne(new EntityWrapper<UserAgent>().eq(UserAgent.USER_ID, userId));
    }
}
