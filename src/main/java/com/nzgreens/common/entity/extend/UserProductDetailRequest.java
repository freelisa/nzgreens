package com.nzgreens.common.entity.extend;

import java.io.Serializable;

/**
 * 用户详情请求类
 * Created by sylar on 2018/4/14.
 * @author sylar
 */
public class UserProductDetailRequest implements Serializable {
    private Long agentUserId;
    private Long productId;

    public Long getAgentUserId() {
        return agentUserId;
    }

    public void setAgentUserId(Long agentUserId) {
        this.agentUserId = agentUserId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "UserProductDetailRequest{" +
                "agentUserId=" + agentUserId +
                ", productId=" + productId +
                '}';
    }
}
