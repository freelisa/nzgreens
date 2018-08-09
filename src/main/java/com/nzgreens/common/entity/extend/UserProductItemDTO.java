package com.nzgreens.common.entity.extend;

/**
 * 用户商品列表
 * Created by sylar on 2018/4/14.
 * @author sylar
 */
public class UserProductItemDTO extends ProductItemDTO {
    private Double agentPrice;

    public Double getAgentPrice() {
        return agentPrice;
    }

    public void setAgentPrice(Double agentPrice) {
        this.agentPrice = agentPrice;
    }

    @Override
    public String toString() {
        return "UserProductItemDTO{" +
                "agentPrice=" + agentPrice +
                "} " + super.toString();
    }
}
