package com.nzgreens.common.common.utils;

import com.baomidou.mybatisplus.entity.Column;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.nzgreens.common.common.enums.DeliveryModeEnum;
import com.nzgreens.common.entity.Users;
import com.nzgreens.common.service.UsersService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 订单号工具类
 * Created by sylar on 2018/4/6.
 * @author sylar
 */
@Component
public class OrderNumberUtils {
    private static final String USER_ORDER_NUMBER_PREFIX = "";
    private static final String AGENT_ORDER_NUMBER_PREFIX = "";
    @Autowired
    private UsersService usersService;
    /**
     * 生成订单号
     * @param userId
     * @param telephone
     * @param deliveryModeEnum
     * @return nullable
     */
    public String generatorOderNumber (Long userId, String telephone, DeliveryModeEnum deliveryModeEnum) {
        if (telephone == null || deliveryModeEnum == null) {
            return null;
        }
        return getOrderNumber(getOrderNumberPrefix(deliveryModeEnum), telephone, userId);
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
     * @param telephone
     * @return
     */
    private String getOrderNumber (String prefix, String telephone, Long userId){
        Users users = usersService.selectOne(new EntityWrapper<Users>().setSqlSelect(Column.create().column(Users.ID),
                Column.create().column(Users.TELEPHONE),
                Column.create().column(Users.LAST_ORDER_NUMBER).as("lastOrderNumber")).eq(Users.ID, userId));
        if (users.getLastOrderNumber() == null || users.getLastOrderNumber() == 0) {
            return users.getTelephone() + "-" + 1;
        }
        return String.valueOf(users.getLastOrderNumber() + 1).replaceAll(users.getTelephone(), users.getTelephone() + "-");
    }



    public static Long getLastOrderNumber(String orderNumber){
        if (StringUtils.isNumeric(orderNumber)) {
            return Long.valueOf(orderNumber);
        }
        String number = orderNumber.replace("-","");
        if (StringUtils.isNumeric(number)) {
            return Long.valueOf(number);
        }
        return 0L;
    }

}
