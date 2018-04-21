package com.nzgreens.common.entity.service;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sylar on 2018/4/21.
 */
public class UserProductPriceSearch implements Serializable{
    private Long agentUserId;
    private List<Long> productIdList;

    public Long getAgentUserId() {
        return agentUserId;
    }

    public void setAgentUserId(Long agentUserId) {
        this.agentUserId = agentUserId;
    }

    public List<Long> getProductIdList() {
        return productIdList;
    }

    public void setProductIdList(List<Long> productIdList) {
        this.productIdList = productIdList;
    }
}
