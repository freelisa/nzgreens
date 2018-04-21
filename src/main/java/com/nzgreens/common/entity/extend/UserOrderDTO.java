package com.nzgreens.common.entity.extend;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sylar on 2018/4/15.
 */
public class UserOrderDTO implements Serializable {
    private String orderNumber;
    private List<Long> orderId;

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public List<Long> getOrderId() {
        return orderId;
    }

    public void setOrderId(List<Long> orderId) {
        this.orderId = orderId;
    }
}
