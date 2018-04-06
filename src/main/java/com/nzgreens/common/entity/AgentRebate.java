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
 * 代理返佣设置表
 * </p>
 *
 * @author sylar
 * @since 2018-04-06
 */
@TableName("agent_rebate")
public class AgentRebate extends Model<AgentRebate> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 代理用户id
     */
	@TableField("agent_user_id")
	private Long agentUserId;
    /**
     * 订单返佣(百分比 80 = 80%)
     */
	@TableField("order_rebate")
	private Long orderRebate;
    /**
     * 代理月返佣（百分比）
     */
	@TableField("month_rebate")
	private Long monthRebate;
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

	public Long getOrderRebate() {
		return orderRebate;
	}

	public void setOrderRebate(Long orderRebate) {
		this.orderRebate = orderRebate;
	}

	public Long getMonthRebate() {
		return monthRebate;
	}

	public void setMonthRebate(Long monthRebate) {
		this.monthRebate = monthRebate;
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

	public static final String ORDER_REBATE = "order_rebate";

	public static final String MONTH_REBATE = "month_rebate";

	public static final String CREATE_TIME = "create_time";

	public static final String UPDATE_TIME = "update_time";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "AgentRebate{" +
			"id=" + id +
			", agentUserId=" + agentUserId +
			", orderRebate=" + orderRebate +
			", monthRebate=" + monthRebate +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}
