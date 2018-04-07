package com.nzgreens.common.mapper;

import com.nzgreens.common.entity.ProductsCrawl;

import java.util.List;


public interface CustomMapper {
    /**
     * 查询抓取的数据是否已经添加到产品表
     * @return
     */
    List<ProductsCrawl> queryProductIsExists();
}
