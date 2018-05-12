package com.nzgreens.common.entity.extend;

/**
 * 用户管理列表实体类
 * Created by sylar on 2018/5/1.
 */
public class UserManageItemDTO {

    private Long userId;
    private String avatar;
    private String nickname;
    private Long balance;


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

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }
}
