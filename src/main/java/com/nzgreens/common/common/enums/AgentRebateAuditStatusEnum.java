package com.nzgreens.common.common.enums;

/**
 * 代理返佣审核状态枚举类
 * Created by sylar on 2018/4/6.
 * @author sylar
 */
public enum AgentRebateAuditStatusEnum {
    _PENDING(0,"未审核"),
    _DONE(1,"已通过"),
    _REFUSED(2,"已拒绝");

    private Integer type;
    private String description;

    AgentRebateAuditStatusEnum(Integer type, String description) {
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
