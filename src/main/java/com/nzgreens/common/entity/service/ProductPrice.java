package com.nzgreens.common.entity.service;

import java.io.Serializable;

/**
 * Created by sylar on 2018/4/21.
 */
public class ProductPrice implements Serializable{
    private Long productId;
    private Long sellingPrice;
    private Long agentPrice;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Long sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Long getAgentPrice() {
        return agentPrice;
    }

    public void setAgentPrice(Long agentPrice) {
        this.agentPrice = agentPrice;
    }
}
