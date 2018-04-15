package com.nzgreens.common.mapper.poss;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.nzgreens.common.entity.AccountLogs;
import com.nzgreens.common.entity.poss.CoinFlowModel;
import com.nzgreens.common.form.CoinFlowForm;

import java.util.List;

/**
 * @Author:helizheng
 * @Date: Created in 2018/4/15 15:35
 */

public interface CoinFlowMapper extends BaseMapper<AccountLogs> {
    /**
     * 查询流水
     * @param form
     * @param page
     * @return
     */
    List<CoinFlowModel> selectCoinFlowForPage(CoinFlowForm form, Pagination page);
}
