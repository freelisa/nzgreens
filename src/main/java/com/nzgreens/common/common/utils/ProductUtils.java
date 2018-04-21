package com.nzgreens.common.common.utils;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.nzgreens.common.entity.ProductFreight;
import com.nzgreens.common.service.ProductFreightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 商品工具类
 * Created by sylar on 2018/4/14.
 * @author sylar
 */
@Component
public class ProductUtils {
    private static final String key = "PRODUCT_FREIGHT";
    private static final String service = "正品保障 · 假一赔十 · 100%海外直邮";
    @Autowired
    private ProductFreightService productFreightService;

    /**
     * 获取运费
     * @param productWeight
     * @return  "35.0（单位：元）"
     */
    public String getFreightSetting (Integer productWeight){
        ProductFreight productFreight = this.getProductFreight();
        if (productFreight == null) {
            return "";
        }
        if (productWeight >= productFreight.getProductWeight()) {
            //TODO 转元
            return String.valueOf(productWeight * productFreight.getFreight());
        }
        return String.valueOf(productFreight.getFreight());
    }

    public String getService (){
        return service;
    }

    /**
     * 计算商品运费
     * @param productTotalWeight
     * @return
     */
    public Long computeFreight (Long productTotalWeight){
        ProductFreight productFreight = this.getProductFreight();
        if (productTotalWeight >= productFreight.getProductWeight()) {
            return productTotalWeight * productFreight.getFreight();
        }
        return productFreight.getFreight();
    }

    private ProductFreight getProductFreight(){
        ProductFreight productFreight = null;
        try {
            productFreight = freightLoadingCache.get(key);
        } catch (ExecutionException e) {
            productFreight = productFreightService.selectOne(null);
        }
        return productFreight;
    }

    private LoadingCache<String,ProductFreight> freightLoadingCache = CacheBuilder.newBuilder()
            .maximumSize(1)
            .refreshAfterWrite(5, TimeUnit.MINUTES)
            .expireAfterWrite(8,TimeUnit.MINUTES)
            .build(
            new CacheLoader<String, ProductFreight>() {
                @Override
                public ProductFreight load(String key) throws Exception {
                    return productFreightService.selectOne(null);
                }
            }
    );

}
