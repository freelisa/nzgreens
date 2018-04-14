package com.nzgreens.common.service;

import com.nzgreens.common.entity.ProductsCrawl;

import java.util.List;

/**
 * @Author:helizheng
 * @Date: Created in 2017/8/6 18:07
 */

public interface CustomService {
    /**
     * 查询抓取的数据是否已经添加到产品表
     * @return
     */
    List<ProductsCrawl> queryProductIsExists();
}
