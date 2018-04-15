package com.nzgreens.common.mapper.poss;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.nzgreens.common.entity.Products;
import com.nzgreens.common.form.ProductForm;

import java.util.List;

/**
 * @Author:helizheng
 * @Date: Created in 2018/4/15 15:35
 */

public interface ProductMapper extends BaseMapper<Products> {
    /**
     * 查询产品列表
     * @param form
     * @param page
     * @return
     */
    List<Products> selectProductForPage(ProductForm form, Pagination page);
}
