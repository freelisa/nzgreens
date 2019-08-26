package com.nzgreens.common.entity.extend;

/**
 * Created by sylar on 2018/5/13.
 */
public class ProductFreightDTO {
    /**
     * 商品重量（单位：克）
     */
    private Long productWeight;
    /**
     * 运费（单位：金币）
     */
    private Double freight;

    private Double minFreight;

    public Long getProductWeight() {
        return productWeight;
    }

    public void setProductWeight(Long productWeight) {
        this.productWeight = productWeight;
    }

    public Double getFreight() {
        return freight;
    }

    public void setFreight(Double freight) {
        this.freight = freight;
    }

    public Double getMinFreight() {
        return this.minFreight;
    }

    public void setMinFreight(Double minFreight) {
        this.minFreight = minFreight;
    }
}
