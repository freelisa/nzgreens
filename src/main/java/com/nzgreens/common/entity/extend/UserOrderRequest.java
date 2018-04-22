package com.nzgreens.common.entity.extend;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sylar on 2018/4/22.
 */
public class UserOrderRequest implements Serializable{
    private Long userId;
    private List<Integer> statusList;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Integer> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<Integer> statusList) {
        this.statusList = statusList;
    }
}
