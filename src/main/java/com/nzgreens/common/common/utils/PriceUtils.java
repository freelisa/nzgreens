package com.nzgreens.common.common.utils;

import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;

/**
 * Created by sylar on 2018/5/12.
 */
public class PriceUtils {

    private static BigDecimal one = new BigDecimal(100);
    /**
     * 分转元
     * @param price
     * @return
     */
    public static String convertPriceToYuan(Number price){
        if (price == null) {
            return "0.0";
        }
        BigDecimal b = new BigDecimal(price.doubleValue()).divide(one);
        double doubleValue = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return String.format("%.2f", doubleValue);
    }

    public static String convertPriceToYuan(String price){
        if (StringUtils.isBlank(price)) {
            return "0.0";
        }
        BigDecimal b = new BigDecimal(Double.valueOf(price)).divide(one);
        double doubleValue = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return String.format("%.2f", doubleValue);
    }

    public static Double convertPriceToYuanNumber(Number price){
        if (price == null) {
            return 0.0;
        }
        BigDecimal b = new BigDecimal(price.doubleValue()).divide(one);
        double doubleValue = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return doubleValue;
    }

    public static Long convertPriceToFen(Number price) {
        if (price == null) {
            return 0L;
        }
        return convertPriceToFen(new BigDecimal(price.doubleValue()));
    }

    public static Long convertPriceToFen(BigDecimal price) {
        if (price == null) {
            return 0L;
        }
        return price.multiply(one).setScale(0,BigDecimal.ROUND_HALF_UP).longValue();
    }
}
