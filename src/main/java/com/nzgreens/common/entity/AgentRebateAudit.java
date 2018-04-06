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
 * 代理返佣审核表
 * </p>
 *
 * @author sylar
 * @since 2018-04-06
 */
@TableName("agent_rebate_audit")
public class AgentRebateAudit extends Model<AgentRebateAudit> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 代理用户id
     */
	@TableField("agent_user_id")
	private Long agentUserId;
    /**
     * 订单号
     */
	@TableField("order_number")
	private Long orderNumber;
    /**
     * 返佣类型（1：订单返佣 2：月返佣）
     */
	private Integer type;
    /**
     * 返佣金额（单位：分）
     */
	@TableField("rebate_price")
	private Long rebatePrice;
    /**
     * 实际返佣金额（单位：分）
     */
	@TableField("actual_rebate_price")
	private Long actualRebatePrice;
    /**
     * 审核状态（0：未审核 1：已通过 -1：已拒绝）
     */
	private Integer status;
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

	public Long getAgentUserId() {
		return agentUserId;
	}

	public void setAgentUserId(Long agentUserId) {
		this.agentUserId = agentUserId;
	}

	public Long getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Long orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getRebatePrice() {
		return rebatePrice;
	}

	public void setRebatePrice(Long rebatePrice) {
		this.rebatePrice = rebatePrice;
	}

	public Long getActualRebatePrice() {
		return actualRebatePrice;
	}

	public void setActualRebatePrice(Long actualRebatePrice) {
		this.actualRebatePrice = actualRebatePrice;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public static final String AGENT_USER_ID = "agent_user_id";

	public static final String ORDER_NUMBER = "order_number";

	public static final String TYPE = "type";

	public static final String REBATE_PRICE = "rebate_price";

	public static final String ACTUAL_REBATE_PRICE = "actual_rebate_price";

	public static final String STATUS = "status";

	public static final String CREATE_TIME = "create_time";

	public static final String UPDATE_TIME = "update_time";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "AgentRebateAudit{" +
			"id=" + id +
			", agentUserId=" + agentUserId +
			", orderNumber=" + orderNumber +
			", type=" + type +
			", rebatePrice=" + rebatePrice +
			", actualRebatePrice=" + actualRebatePrice +
			", status=" + status +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}
