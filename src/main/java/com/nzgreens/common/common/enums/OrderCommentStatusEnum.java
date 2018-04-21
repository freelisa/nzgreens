package com.nzgreens.common.common.enums;

/**
 * Created by sylar on 2018/4/21.
 */
public enum  OrderCommentStatusEnum {
    _NOT_COMMENTED(0,"未评价"),
    _COMMENTED(1,"已评价"),;

    private Integer status;
    private String description;

    OrderCommentStatusEnum(Integer status, String description) {
        this.status = status;
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }
}
