package com.nzgreens.common.service;

import com.nzgreens.common.common.enums.AccountLogsTypeEnum;
import com.nzgreens.common.common.result.BaseResponse;
import com.nzgreens.common.entity.Users;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author sylar
 * @since 2018-04-05
 */
public interface UsersService extends IService<Users> {

    /**
     * 操作用户余额
     * @param charge
     * @param userAgent
     * @param userId
     * @param amount
     */
    BaseResponse<Boolean> operatorUserBalanceTx(AccountLogsTypeEnum charge, Users userAgent, Long userId, Integer amount) throws Exception;
}
