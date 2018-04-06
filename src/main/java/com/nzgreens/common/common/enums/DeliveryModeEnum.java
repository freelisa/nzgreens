package com.nzgreens.common.common.enums;

/**
 * 收货方式
 * Created by sylar on 2018/4/6.
 * @author sylar
 */
public enum DeliveryModeEnum {
    _SELF(1,"自收","直接购买"),
    _DELIVERY(2,"代收","合并订单"),;

    private Integer type;
    private String userMode;
    private String agentMode;

    DeliveryModeEnum(Integer type, String userMode, String agentMode) {
        this.type = type;
        this.userMode = userMode;
        this.agentMode = agentMode;
    }

    public Integer getType() {
        return type;
    }

    public String getUserMode() {
        return userMode;
    }

    public String getAgentMode() {
        return agentMode;
    }
}
