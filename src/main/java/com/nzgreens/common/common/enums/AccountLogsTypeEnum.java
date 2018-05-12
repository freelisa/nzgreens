package com.nzgreens.common.common.enums;

/**
 * 账户流水类型
 * Created by sylar on 2018/4/6.
 * @author sylar
 */
public enum AccountLogsTypeEnum {
    _CHARGE(1,"充值"),
    _ORDER_REBATE(2,"订单返佣"),
    _ORDER(3,"下单"),
    _REFUND(4,"退款"),
    _MONTH_REBATE(5,"月返佣"),
    _WITHDRAW(6,"提现"),
    _ORDER_REFUSED(7,"订单驳回"),;

    private Integer type;
    private String description;

    AccountLogsTypeEnum(Integer type, String description) {
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
