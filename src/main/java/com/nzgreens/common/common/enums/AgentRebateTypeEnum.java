package com.nzgreens.common.common.enums;

/**
 * 代理返佣类型枚举
 * Created by sylar on 2018/4/6.
 * @author sylar
 */
public enum AgentRebateTypeEnum {
    _ORDER(1,"订单返佣"),
    _MONTH(2,"月返佣"),;

    private Integer type;
    private String description;

    AgentRebateTypeEnum(Integer type, String description) {
        this.type = type;
        this.description = description;
    }

    public Integer getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }
}
