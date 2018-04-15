package com.nzgreens.common.form;

/**
 * @Author:helizheng
 * @Date: Created in 2018/4/15 17:45
 */

public class ProductForm {
    private String title;

    private Long brandId;

    private Long categoryId;

    private Integer isValid;

    public static final String TITLE = "title";
    public static final String BRAND_ID = "brand_id";
    public static final String CATEGORY_ID = "category_id";
    public static final String IS_VALID = "is_valid";

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }
}
