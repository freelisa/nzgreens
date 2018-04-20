package com.nzgreens.common.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.nzgreens.common.entity.poss.CoinFlowModel;
import com.nzgreens.common.form.CoinFlowForm;

/**
 * @Author:helizheng
 * @Date: Created in 2018/4/15 16:00
 */

public interface CoinFlowService {
    Page<CoinFlowModel> selectCoinFlowForPage(CoinFlowForm form, Page page) throws Exception;
}
