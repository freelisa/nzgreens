package com.nzgreens.common.service.impl;

import com.nzgreens.common.entity.AccountLogs;
import com.nzgreens.common.mapper.AccountLogsMapper;
import com.nzgreens.common.service.AccountLogsService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 账户流水表 服务实现类
 * </p>
 *
 * @author sylar
 * @since 2018-04-05
 */
@Service
public class AccountLogsServiceImpl extends ServiceImpl<AccountLogsMapper, AccountLogs> implements AccountLogsService {
	
}
