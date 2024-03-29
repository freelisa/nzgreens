package com.nzgreens.common.entity.extend;

import java.util.List;

/**
 * Created by sylar on 2018/4/30.
 */
public class OrderManageItemDTO {
    private String orderNumber;
    private String productPrice;
    private String freight;
    private String price;
    private Integer userOrderStatus;
    private String nickname;
    private Boolean canAdopt;
    private Integer totalNumber;
    private Double agentOrderPrice;
    private boolean refuseShow;

    private List<ManageItemDTO> orderItemList;
    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getFreight() {
        return freight;
    }

    public void setFreight(String freight) {
        this.freight = freight;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getUserOrderStatus() {
        return userOrderStatus;
    }

    public void setUserOrderStatus(Integer userOrderStatus) {
        this.userOrderStatus = userOrderStatus;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public List<ManageItemDTO> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<ManageItemDTO> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public Boolean getCanAdopt() {
        return canAdopt;
    }

    public void setCanAdopt(Boolean canAdopt) {
        this.canAdopt = canAdopt;
    }

    public Integer getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(Integer totalNumber) {
        this.totalNumber = totalNumber;
    }

    public Double getAgentOrderPrice() {
        return agentOrderPrice;
    }

    public void setAgentOrderPrice(Double agentOrderPrice) {
        this.agentOrderPrice = agentOrderPrice;
    }

    public boolean isRefuseShow() {
        return refuseShow;
    }

    public void setRefuseShow(boolean refuseShow) {
        this.refuseShow = refuseShow;
    }
}
