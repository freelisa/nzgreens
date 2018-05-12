package com.nzgreens.common.common.enums;

/**
 * Created by sylar on 2018/4/21.
 */
public enum  UserOrderAgentStatusEnum  {
    _REFUSED(-1,"订单已拒绝"),
    _PENDING(0,"待处理"),
    _PROCESSED(1,"已通过"),
    _DONE(2,"已合并"),;

    private Integer status;
    private String description;

    UserOrderAgentStatusEnum(Integer status, String description) {
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
