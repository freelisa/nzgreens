package com.nzgreens.common.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nzgreens.common.entity.Products;
import com.nzgreens.common.form.ProductForm;
import com.nzgreens.common.mapper.poss.ProductMapper;
import com.nzgreens.common.service.ProductPossService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author:helizheng
 * @Date: Created in 2018/4/15 18:23
 */
@Service
public class ProductPossServiceImpl extends ServiceImpl<ProductMapper, Products> implements ProductPossService {
    @Resource
    private ProductMapper productMapper;

    @Override
    public Page<Products> selectProductForPage(ProductForm form, Page page) throws Exception {
        List<Products> products = productMapper.selectProductForPage(form, page);
        page.setRecords(products);
        return page;
    }
}
