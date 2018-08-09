package com.nzgreens.common.entity.extend;

/**
 * 用户管理列表实体类
 * Created by sylar on 2018/5/1.
 */
public class UserManageItemDTO {

    private Long userId;
    private String avatar;
    private String nickname;
    private Double balance;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
