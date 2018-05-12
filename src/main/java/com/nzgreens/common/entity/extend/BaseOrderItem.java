package com.nzgreens.common.entity.extend;

/**
 * Created by sylar on 2018/5/7.
 */
public class BaseOrderItem {
    private String orderId;
    private String title;
    private String image;
    private String weight;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
