package com.nzgreens.common.service.impl;

import com.nzgreens.common.common.enums.AccountLogsTypeEnum;
import com.nzgreens.common.common.result.BaseResponse;
import com.nzgreens.common.entity.AccountLogs;
import com.nzgreens.common.entity.ChargeRecord;
import com.nzgreens.common.entity.Users;
import com.nzgreens.common.entity.WithdrawRecord;
import com.nzgreens.common.mapper.UsersMapper;
import com.nzgreens.common.service.AccountLogsService;
import com.nzgreens.common.service.ChargeRecordService;
import com.nzgreens.common.service.UsersService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nzgreens.common.service.WithdrawRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    @Autowired
    private ChargeRecordService chargeRecordService;
    @Autowired
    private WithdrawRecordService withdrawRecordService;
    @Autowired
    private AccountLogsService accountLogsService;

    @Override
    public BaseResponse<Boolean> operatorUserBalanceTx(AccountLogsTypeEnum logsTypeEnum, Users userAgent, Long userId, Integer amount) throws Exception {
        BaseResponse<Boolean> response = new BaseResponse<>();
        List<AccountLogs> accountLogsList = new ArrayList<>();
        List<Users> usersList = new ArrayList<>();
        Users users = this.selectById(userId);
        if (users == null) {
            response.setSuccess(false);
            response.setMsg("用户不存在！");
            return response;
        }
        //充值
        if (AccountLogsTypeEnum._CHARGE.equals(logsTypeEnum)) {
/*            if (userAgent.getBalance() - amount < 0) {
                response.setSuccess(false);
                response.setMsg("您当前余额不足，无法操作！");
                return response;
            }*/

            ChargeRecord chargeRecord = new ChargeRecord();
            chargeRecord.setUserAgentId(userAgent.getId());
            chargeRecord.setUserId(userId);
            chargeRecord.setAmount(amount.longValue());
            chargeRecordService.insert(chargeRecord);

            AccountLogs agentLogs = new AccountLogs();
            agentLogs.setUserId(userAgent.getId());
            agentLogs.setTriggerUserId(userAgent.getId());
            agentLogs.setRecordId(chargeRecord.getId());
            agentLogs.setType(logsTypeEnum.getType());
            agentLogs.setBefore(userAgent.getBalance());
            agentLogs.setAmount(-amount.longValue());
            agentLogs.setAfter(agentLogs.getBefore() + agentLogs.getAmount());

            userAgent.setBalance(agentLogs.getAfter());

            AccountLogs userLogs = new AccountLogs();
            userLogs.setUserId(users.getId());
            userLogs.setTriggerUserId(userAgent.getId());
            userLogs.setRecordId(chargeRecord.getId());
            userLogs.setType(logsTypeEnum.getType());
            userLogs.setBefore(users.getBalance());
            userLogs.setAmount(amount.longValue());
            userLogs.setAfter(userLogs.getBefore() + userLogs.getAmount());

            users.setBalance(userLogs.getAfter());

            accountLogsList.add(agentLogs);
            accountLogsList.add(userLogs);
            usersList.add(userAgent);
            usersList.add(users);
        } else if (AccountLogsTypeEnum._WITHDRAW.equals(logsTypeEnum)){
/*            if (users.getBalance() - amount < 0) {
                response.setSuccess(false);
                response.setMsg("用户余额不足，无法操作！");
                return response;
            }*/
            WithdrawRecord withdrawRecord = new WithdrawRecord();
            withdrawRecord.setUserAgentId(userAgent.getId());
            withdrawRecord.setUserId(userId);
            withdrawRecord.setAmount(amount.longValue());
            withdrawRecordService.insert(withdrawRecord);

            AccountLogs agentLogs = new AccountLogs();
            agentLogs.setUserId(userAgent.getId());
            agentLogs.setTriggerUserId(userAgent.getId());
            agentLogs.setRecordId(withdrawRecord.getId());
            agentLogs.setType(logsTypeEnum.getType());
            agentLogs.setBefore(userAgent.getBalance());
            agentLogs.setAmount(amount.longValue());
            agentLogs.setAfter(agentLogs.getBefore() + agentLogs.getAmount());

            userAgent.setBalance(agentLogs.getAfter());

            AccountLogs userLogs = new AccountLogs();
            userLogs.setUserId(users.getId());
            userLogs.setTriggerUserId(userAgent.getId());
            userLogs.setRecordId(withdrawRecord.getId());
            userLogs.setType(logsTypeEnum.getType());
            userLogs.setBefore(users.getBalance());
            userLogs.setAmount(-amount.longValue());
            userLogs.setAfter(userLogs.getBefore() + userLogs.getAmount());

            users.setBalance(userLogs.getAfter());

            accountLogsList.add(agentLogs);
            accountLogsList.add(userLogs);
            usersList.add(userAgent);
            usersList.add(users);
        } else if (AccountLogsTypeEnum._REFUND.equals(logsTypeEnum)) {
/*            if (userAgent.getBalance() - amount < 0) {
                response.setSuccess(false);
                response.setMsg("您当前余额不足，无法操作！");
                return response;
            }*/
            AccountLogs agentLogs = new AccountLogs();
            agentLogs.setUserId(userAgent.getId());
            agentLogs.setTriggerUserId(userAgent.getId());
            agentLogs.setType(logsTypeEnum.getType());
            agentLogs.setBefore(userAgent.getBalance());
            agentLogs.setAmount(-amount.longValue());
            agentLogs.setAfter(agentLogs.getBefore() + agentLogs.getAmount());

            userAgent.setBalance(agentLogs.getAfter());

            AccountLogs userLogs = new AccountLogs();
            userLogs.setUserId(users.getId());
            userLogs.setTriggerUserId(userAgent.getId());
            userLogs.setType(logsTypeEnum.getType());
            userLogs.setBefore(users.getBalance());
            userLogs.setAmount(amount.longValue());
            userLogs.setAfter(userLogs.getBefore() + userLogs.getAmount());

            users.setBalance(userLogs.getAfter());

            accountLogsList.add(agentLogs);
            accountLogsList.add(userLogs);
            usersList.add(userAgent);
            usersList.add(users);
        }
        this.updateBatchById(usersList);
        accountLogsService.insertBatch(accountLogsList);
        response.setSuccess(true);
        return response;
    }
}
