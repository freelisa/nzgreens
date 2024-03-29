package com.nzgreens.common.entity;

import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author sylar
 * @since 2018-06-09
 */
public class Users extends Model<Users> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 手机号
     */
	private String telephone;
    /**
     * 登录密码（MD5）
     */
	private String password;
    /**
     * 用户昵称
     */
	private String nickname;
    /**
     * 用户头像
     */
	private String avatar;
    /**
     * 用户类型(0:系统 1:普通用户 2:代理)
     */
	private Integer type;
    /**
     * 余额
     */
	private Long balance;
	private String remark;
    /**
     * 订单号自增开始
     */
	@TableField("last_order_number")
	private Long lastOrderNumber;
    /**
     * 是否有效(1：有效 0：无效)
     */
	@TableField("is_valid")
	@TableLogic
	private Integer isValid;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;
    /**
     * 更新时间
     */
	@TableField("update_time")
	private Date updateTime;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getBalance() {
		return balance;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getLastOrderNumber() {
		return lastOrderNumber;
	}

	public void setLastOrderNumber(Long lastOrderNumber) {
		this.lastOrderNumber = lastOrderNumber;
	}

	public Integer getIsValid() {
		return isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public static final String ID = "id";

	public static final String TELEPHONE = "telephone";

	public static final String PASSWORD = "password";

	public static final String NICKNAME = "nickname";

	public static final String AVATAR = "avatar";

	public static final String TYPE = "type";

	public static final String BALANCE = "balance";

	public static final String REMARK = "remark";

	public static final String LAST_ORDER_NUMBER = "last_order_number";

	public static final String IS_VALID = "is_valid";

	public static final String CREATE_TIME = "create_time";

	public static final String UPDATE_TIME = "update_time";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Users{" +
			"id=" + id +
			", telephone=" + telephone +
			", password=" + password +
			", nickname=" + nickname +
			", avatar=" + avatar +
			", type=" + type +
			", balance=" + balance +
			", remark=" + remark +
			", lastOrderNumber=" + lastOrderNumber +
			", isValid=" + isValid +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}
