package com.nzgreens.common.common.enums;

/**
 * 用户订单状态枚举类
 * Created by sylar on 2018/4/6.
 * @author sylar
 */
public enum UserOrderStatusEnum {
    _REFUSED(-1,"已驳回"),
    _PENDING(0,"待处理"),
    _PROCESSED(1,"已处理/代发货"),
    _DONE(2,"已上传凭证");

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

    public static boolean isOrderCompleted (Integer orderStatus, UserTypeEnum userTypeEnum){
        if (UserTypeEnum._AGENT.equals(userTypeEnum)) {
            return _DONE.getStatus().equals(orderStatus);
        } else {
            return _PROCESSED.getStatus().equals(orderStatus);
        }
    }

    public static Integer getUserOrderStatus(Integer orderStatus, UserTypeEnum userTypeEnum){
        if (!UserTypeEnum._AGENT.equals(userTypeEnum)) {
            if (UserOrderStatusEnum._PROCESSED.getStatus().equals(orderStatus)) {
                return Integer.valueOf(2);
            }
            return orderStatus;
        } else {
            return orderStatus;
        }
    }
}
