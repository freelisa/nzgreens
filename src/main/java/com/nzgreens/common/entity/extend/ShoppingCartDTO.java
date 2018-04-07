package com.nzgreens.common.entity.extend;

import java.io.Serializable;

/**
 * Created by sylar on 2018/4/7.
 */
public class ShoppingCartDTO implements Serializable {
    private Long id;
    private Integer productNumber;
    private String title;
    private String image;
    private Integer agentPrice;
    private Integer costPrice;
    private Integer sellingPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(Integer productNumber) {
        this.productNumber = productNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getAgentPrice() {
        return agentPrice;
    }

    public void setAgentPrice(Integer agentPrice) {
        this.agentPrice = agentPrice;
    }

    public Integer getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Integer costPrice) {
        this.costPrice = costPrice;
    }

    public Integer getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Integer sellingPrice) {
        this.sellingPrice = sellingPrice;
    }
}
