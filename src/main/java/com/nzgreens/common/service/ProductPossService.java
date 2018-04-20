package com.nzgreens.common.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.nzgreens.common.entity.Products;
import com.nzgreens.common.form.ProductForm;

/**
 * @Author:helizheng
 * @Date: Created in 2018/4/15 18:23
 */

public interface ProductPossService extends IService<Products> {
    Page<Products> selectProductForPage(ProductForm form, Page page) throws Exception;
}
