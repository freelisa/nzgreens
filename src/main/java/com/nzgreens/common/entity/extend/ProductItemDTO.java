package com.nzgreens.common.entity.extend;

import java.io.Serializable;

/**
 * 商品列表
 * Created by sylar on 2018/4/14.
 * @author sylar
 */
public class ProductItemDTO implements Serializable{
    private Long id;
    private String title;
    private String image;
    private String sellingPrice;
    private String salesVolume;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(String sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public String getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(String salesVolume) {
        this.salesVolume = salesVolume;
    }

    @Override
    public String toString() {
        return "ProductItemDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", sellingPrice='" + sellingPrice + '\'' +
                ", salesVolume='" + salesVolume + '\'' +
                '}';
    }
}
