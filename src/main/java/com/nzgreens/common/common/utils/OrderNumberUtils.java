package com.nzgreens.common.common.utils;

import com.nzgreens.common.common.enums.DeliveryModeEnum;

import java.util.Date;

/**
 * 订单号工具类
 * Created by sylar on 2018/4/6.
 * @author sylar
 */
public class OrderNumberUtils {
    private static final String USER_ORDER_NUMBER_PREFIX = "10";
    private static final String AGENT_ORDER_NUMBER_PREFIX = "20";

    /**
     * 生成订单号
     * @param userId
     * @param deliveryModeEnum
     * @return nullable
     */
    public static String generatorOderNumber (Long userId, DeliveryModeEnum deliveryModeEnum) {
        if (userId == null || deliveryModeEnum == null) {
            return null;
        }
        return getOrderNumber(getOrderNumberPrefix(deliveryModeEnum), userId);
    }

    /**
     * 获取订单号前缀
     * @param deliveryModeEnum
     * @return
     */
    private static String getOrderNumberPrefix(DeliveryModeEnum deliveryModeEnum){
        switch (deliveryModeEnum) {
            case _SELF:
                return USER_ORDER_NUMBER_PREFIX;
            case _DELIVERY:
                return AGENT_ORDER_NUMBER_PREFIX;
        }
        return null;
    }

    /**
     * 获取订单号
     * @param prefix
     * @param userId
     * @return
     */
    private static String getOrderNumber (String prefix, Long userId){
        return prefix + userId + DateUtil.parseFullDateString(new Date()) ;
    }
}
