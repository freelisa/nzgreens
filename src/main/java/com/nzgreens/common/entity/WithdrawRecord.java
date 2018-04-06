package com.nzgreens.common.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 提现表
 * </p>
 *
 * @author sylar
 * @since 2018-04-06
 */
@TableName("withdraw_record")
public class WithdrawRecord extends Model<WithdrawRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 代理用户ID
     */
	@TableField("user_agent_id")
	private Long userAgentId;
    /**
     * 用户id
     */
	@TableField("user_id")
	private Long userId;
    /**
     * 金额
     */
	private Long amount;
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

	public Long getUserAgentId() {
		return userAgentId;
	}

	public void setUserAgentId(Long userAgentId) {
		this.userAgentId = userAgentId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
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

	public static final String USER_AGENT_ID = "user_agent_id";

	public static final String USER_ID = "user_id";

	public static final String AMOUNT = "amount";

	public static final String CREATE_TIME = "create_time";

	public static final String UPDATE_TIME = "update_time";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "WithdrawRecord{" +
			"id=" + id +
			", userAgentId=" + userAgentId +
			", userId=" + userId +
			", amount=" + amount +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}
