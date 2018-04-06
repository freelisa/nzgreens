package com.nzgreens.common.common.enums;

/**
 * 订单状态表
 * Created by sylar on 2018/4/6.
 * @author sylar
 */
public enum OrderStatusEnum {
    _PENDING(0,"未合并"),
    _PROCESSED(1,"已合并"),
    _DONE(2,"已处理"),;

    private Integer status;
    private String description;

    OrderStatusEnum(Integer status, String description) {
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
