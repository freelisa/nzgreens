package com.nzgreens.common.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.nzgreens.common.entity.poss.CoinFlowModel;
import com.nzgreens.common.form.CoinFlowForm;
import com.nzgreens.common.mapper.poss.CoinFlowMapper;
import com.nzgreens.common.service.CoinFlowService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author:helizheng
 * @Date: Created in 2018/4/15 16:02
 */

@Service
public class CoinFlowServiceImpl implements CoinFlowService {
    @Resource
    private CoinFlowMapper coinFlowMapper;

    @Override
    public Page<CoinFlowModel> selectCoinFlowForPage(CoinFlowForm form, Page page) throws Exception {
        List<CoinFlowModel> coinFlowModels = coinFlowMapper.selectCoinFlowForPage(form, page);
        page.setRecords(coinFlowModels);
        return page;
    }
}
