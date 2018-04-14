package com.nzgreens.common.entity.extend;

/**
 * 用户商品详情
 * Created by sylar on 2018/4/14.
 * @author sylar
 */
public class UserProductDetailDTO extends ProductDetailDTO {
    private Long agentPrice;

    public Long getAgentPrice() {
        return agentPrice;
    }

    public void setAgentPrice(Long agentPrice) {
        this.agentPrice = agentPrice;
    }

    @Override
    public String toString() {
        return "UserProductDetailDTO{" +
                "agentPrice=" + agentPrice +
                "} " + super.toString();
    }
}
