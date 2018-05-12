package com.nzgreens.common.entity.extend;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sylar on 2018/4/22.
 */
public class UserOrderRequest implements Serializable{
    private List<Long> userList;
    private List<Integer> statusList;
    private Integer commentStatus;
    private Integer orderType;

    public List<Long> getUserList() {
        return userList;
    }

    public void setUserList(List<Long> userList) {
        this.userList = userList;
    }

    public List<Integer> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<Integer> statusList) {
        this.statusList = statusList;
    }

    public Integer getCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(Integer commentStatus) {
        this.commentStatus = commentStatus;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }
}
