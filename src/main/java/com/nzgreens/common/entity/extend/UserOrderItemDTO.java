package com.nzgreens.common.entity.extend;

import java.util.List;

/**
 * Created by sylar on 2018/4/22.
 */
public class UserOrderItemDTO {

    private String orderNumber;
    private Integer userOrderStatus;
    private String productPrice;
    private String freight;
    private String price;
    private Integer productTotalNumber;
    private Boolean canResend;
    private List<OrderItemDTO> orderItemList;

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getUserOrderStatus() {
        return userOrderStatus;
    }

    public void setUserOrderStatus(Integer userOrderStatus) {
        this.userOrderStatus = userOrderStatus;
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

    public Integer getProductTotalNumber() {
        return productTotalNumber;
    }

    public void setProductTotalNumber(Integer productTotalNumber) {
        this.productTotalNumber = productTotalNumber;
    }

    public Boolean getCanResend() {
        return canResend;
    }

    public void setCanResend(Boolean canResend) {
        this.canResend = canResend;
    }

    public List<OrderItemDTO> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItemDTO> orderItemList) {
        this.orderItemList = orderItemList;
    }
}
