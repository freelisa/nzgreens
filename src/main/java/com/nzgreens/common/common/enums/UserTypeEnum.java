package com.nzgreens.common.common.enums;

import com.nzgreens.common.entity.Users;

/**
 * 用户类型枚举类
 * Created by sylar on 2018/4/6.
 * @author sylar
 */
public enum UserTypeEnum {
    _SYSTEM(0,"系统"),
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

    /**
     * 是否是代理身份
     * @param users
     * @return
     */
    public static boolean isAgent (Users users){
        if (users == null) {
            return false;
        }
        return _AGENT.getType().equals(users.getType());
    }

    public static UserTypeEnum getUserTypeEnum (Users users){
        if (users == null) {
            return _USER;
        }
        for (UserTypeEnum userTypeEnum : UserTypeEnum.values()) {
            if (userTypeEnum.getType().equals(users.getType())) {
                return userTypeEnum;
            }
        }
        return _USER;
    }
}
