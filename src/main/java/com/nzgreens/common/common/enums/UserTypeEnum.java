package com.nzgreens.common.common.enums;

/**
 * 用户类型枚举类
 * Created by sylar on 2018/4/6.
 * @author sylar
 */
public enum UserTypeEnum {
    _USER(1,"用户"),
    _AGENT(2,"代理"),;

    private Integer type;
    private String description;

    UserTypeEnum(Integer type, String description) {
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
