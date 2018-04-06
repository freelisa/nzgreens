package com.nzgreens.common.common.enums;

/**
 * 用户订单状态枚举类
 * Created by sylar on 2018/4/6.
 * @author sylar
 */
public enum UserOrderStatusEnum {
    _REFUSED(-1,"订单已拒绝"),
    _PENDING(0,"待处理"),
    _PROCESSED(1,"已处理"),
    _DONE(2,"已上传凭证"),;

    private Integer status;
    private String description;

    UserOrderStatusEnum(Integer status, String description) {
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
