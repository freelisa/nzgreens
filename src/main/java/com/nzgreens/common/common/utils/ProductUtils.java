package com.nzgreens.common.common.utils;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.nzgreens.common.common.enums.DeliveryModeEnum;
import com.nzgreens.common.common.enums.UserTypeEnum;
import com.nzgreens.common.entity.ProductFreight;
import com.nzgreens.common.entity.extend.ProductFreightDTO;
import com.nzgreens.common.service.ProductFreightService;
import org.springframework.beans.BeanUtils;
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
     * 获取商品单位运费
     * @return
     */
    public ProductFreightDTO getFreightUnit(){
        return this.getProductFreight();
    }

    /**
     * 获取运费
     * @return  "35.0（单位：元）"
     */
    public String getFreightSetting (){
      /*  ProductFreightDTO productFreight = this.getFreightUnit();
        if (productFreight == null) {
            return "35/kg";
        }
        BigDecimal price = new BigDecimal(1000).divide(new BigDecimal(productFreight.getProductWeight()));
        String fright = price.multiply(new BigDecimal(productFreight.getFreight())).toString();
        return fright+"/kg";*/
       return "免运费";
    }

    public String getService (){
        return service;
    }

    /**
     * 计算商品运费
     * @param productTotalWeight
     * @return
     */
    public Long computeFreight (Long productTotalWeight, DeliveryModeEnum deliveryModeEnum, UserTypeEnum userTypeEnum){
        ProductFreight productFreight = null;
        try {
            productFreight = freightLoadingCache.get(key);
        } catch (ExecutionException e) {
            productFreight = productFreightService.selectOne(null);
        }
        if (productTotalWeight == null || productTotalWeight == 0L) {
            return productFreight.getFreight();
        }
        if (DeliveryModeEnum._DELIVERY.equals(deliveryModeEnum)) {
            return Long.valueOf(productTotalWeight.longValue() * productFreight.getFreight().longValue() / productFreight.getProductWeight().longValue());
        }
        if (productTotalWeight.longValue() >= productFreight.getProductWeight().longValue()) {
            return Long.valueOf(productTotalWeight.longValue() * productFreight.getFreight().longValue() / productFreight.getProductWeight().longValue());
        }
        return productFreight.getMinFreight();
    }

    private ProductFreightDTO getProductFreight(){
        ProductFreightDTO freightDTO = new ProductFreightDTO();
        ProductFreight productFreight = null;
        try {
            productFreight = freightLoadingCache.get(key);
        } catch (ExecutionException e) {
            productFreight = productFreightService.selectOne(null);
        }
        if (productFreight != null) {
            BeanUtils.copyProperties(productFreight, freightDTO);
        }
        freightDTO.setFreight(PriceUtils.convertPriceToYuanNumber(productFreight.getFreight()));
        freightDTO.setMinFreight(PriceUtils.convertPriceToYuanNumber(productFreight.getMinFreight()));
        return freightDTO;
    }

    private LoadingCache<String,ProductFreight> freightLoadingCache = CacheBuilder.newBuilder()
            .maximumSize(1)
            .refreshAfterWrite(5, TimeUnit.MINUTES)
            .expireAfterWrite(8,TimeUnit.MINUTES)
            .build(
            new CacheLoader<String, ProductFreight>() {
                @Override
                public ProductFreight load(String key) throws Exception {
                    ProductFreight freightDTO = new ProductFreight();
                    freightDTO.setProductWeight(1000L);
                    freightDTO.setFreight(3500L);
                    ProductFreight productFreight = productFreightService.selectOne(null);
                    if (productFreight != null) {
                        BeanUtils.copyProperties(productFreight, freightDTO);
                    }
                    return freightDTO;
                }
            }
    );

}
