package com.nzgreens.common.entity.extend;

import java.io.Serializable;

/**
 * Created by sylar on 2018/4/9.
 * @author sylar
 */
public class ShoppingCartDTO implements Serializable {
    private Long id;
    private Long productId;
    private Integer productNumber;
    private String title;
    private String image;
    private Integer weight;
    private Double sellingPrice;

    private ProductFreightDTO productFreight;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
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

    @Override
    public String toString() {
        return "ShoppingCartDTO{" +
                "id=" + id +
                ", productId=" + productId +
                ", productNumber=" + productNumber +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", weight='" + weight + '\'' +
                ", sellingPrice=" + sellingPrice +
                '}';
    }
}
