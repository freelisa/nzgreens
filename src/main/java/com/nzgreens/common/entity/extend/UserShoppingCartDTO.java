package com.nzgreens.common.entity.extend;

import java.io.Serializable;

/**
 * 普通用户购物车
 * Created by sylar on 2018/4/7.
 * @author sylar
 */
public class UserShoppingCartDTO extends ShoppingCartDTO implements Serializable {
    private Integer agentPrice;

    public Integer getAgentPrice() {
        return agentPrice;
    }

    public void setAgentPrice(Integer agentPrice) {
        this.agentPrice = agentPrice;
    }

    @Override
    public String toString() {
        return "UserShoppingCartDTO{" +
                "agentPrice=" + agentPrice +
                "} " + super.toString();
    }
}
