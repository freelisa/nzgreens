package com.nzgreens.common.common.enums;

/**
 * 用户订单类型枚举
 * Created by sylar on 2018/4/6.
 * @author sylar
 */
public enum UserOrderTypeEnum {
    _AGENT(1,"代理处理订单"),
    _SYSTEM(2,"系统处理订单"),;

    private Integer type;
    private String description;

    UserOrderTypeEnum(Integer type, String description) {
        this.type = type;
        this.description = description;
    }

    public Integer getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public static UserOrderTypeEnum getUserOrderType(Integer type){
        for (UserOrderTypeEnum userOrderTypeEnum : UserOrderTypeEnum.values()) {
            if (userOrderTypeEnum.getType().equals(type)) {
                return userOrderTypeEnum;
            }
        }
        return null;
    }
}
