package com.nzgreens.common.entity.extend;


/**
 * Created by sylar on 2018/5/7.
 */
public class BaseOrderItem {
    private String orderId;
    private Long productId;
    private String title;
    private String image;
    private Long weight;
    private Integer productNumber;
    private Double sellingPrice;
    private ProductFreightDTO productFreight;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public Integer getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(Integer productNumber) {
        this.productNumber = productNumber;
    }

    public Double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public ProductFreightDTO getProductFreight() {
        return productFreight;
    }

    public void setProductFreight(ProductFreightDTO productFreight) {
        this.productFreight = productFreight;
    }
}
