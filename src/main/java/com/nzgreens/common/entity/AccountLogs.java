package com.nzgreens.common.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * 账户流水表
 * </p>
 *
 * @author sylar
 * @since 2018-04-05
 */
@TableName("account_logs")
public class AccountLogs extends Model<AccountLogs> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 账户流水id
     */
	@TableField("record_id")
	private Long recordId;
    /**
     * 用户id
     */
	@TableField("user_id")
	private Long userId;
    /**
     * 流水类型（1：充值 2：订单返佣 3：购买 4：退款 5：月返佣 6：提现 7:订单拒绝）
     */
	private Integer type;
    /**
     * 流水前余额
     */
	private Long before;
    /**
     * 金额
     */
	private Long amount;
    /**
     * 流水后余额
     */
	private Long after;
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

	public Long getRecordId() {
		return recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getBefore() {
		return before;
	}

	public void setBefore(Long before) {
		this.before = before;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Long getAfter() {
		return after;
	}

	public void setAfter(Long after) {
		this.after = after;
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

	public static final String RECORD_ID = "record_id";

	public static final String USER_ID = "user_id";

	public static final String TYPE = "type";

	public static final String BEFORE = "before";

	public static final String AMOUNT = "amount";

	public static final String AFTER = "after";

	public static final String CREATE_TIME = "create_time";

	public static final String UPDATE_TIME = "update_time";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "AccountLogs{" +
			"id=" + id +
			", recordId=" + recordId +
			", userId=" + userId +
			", type=" + type +
			", before=" + before +
			", amount=" + amount +
			", after=" + after +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}
