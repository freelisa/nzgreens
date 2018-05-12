package com.nzgreens.common.entity.extend;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sylar on 2018/4/14.
 * 商品详情
 */
public class ProductDetailDTO extends ProductItemDTO implements Serializable {
   // private Long id;

   // private String image;
    private List<String> imageList;
    private String costPrice;
   // private String sellingPrice;
    private Long stock;

   // private String title;

    private String freight;
  //  private String salesVolume;
    private String brand;

    private String service;

    private String detail;
    private Integer weight;
    private String commentNumber;


    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }

    public String getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(String costPrice) {
        this.costPrice = costPrice;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public String getFreight() {
        return freight;
    }

    public void setFreight(String freight) {
        this.freight = freight;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(String commentNumber) {
        this.commentNumber = commentNumber;
    }
}
