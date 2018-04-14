package com.nzgreens.common.entity.extend;

import java.io.Serializable;

/**
 * 商品模糊搜索结果
 * Created by sylar on 2018/4/14.
 * @author sylar
 */
public class ProductFuzzyDTO implements Serializable {
    private Long id;
    private String title;

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
}
