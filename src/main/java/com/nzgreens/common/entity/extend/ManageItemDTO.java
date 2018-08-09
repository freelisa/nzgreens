package com.nzgreens.common.entity.extend;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by sylar on 2018/5/7.
 */
public class ManageItemDTO extends BaseOrderItem{
    @JSONField(serialize = false)
    private Long agentPrice;
    @JSONField(serialize = false)
    private Integer orderStatus;
    private Double productTotalPrice;

    public Long getAgentPrice() {
        return agentPrice;
    }

    public void setAgentPrice(Long agentPrice) {
        this.agentPrice = agentPrice;
    }

    public Double getProductTotalPrice() {
        return productTotalPrice;
    }

    public void setProductTotalPrice(Double productTotalPrice) {
        this.productTotalPrice = productTotalPrice;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }
}
