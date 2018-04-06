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
 * 用户代理关联表
 * </p>
 *
 * @author sylar
 * @since 2018-04-06
 */
@TableName("user_agent")
public class UserAgent extends Model<UserAgent> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 用户id
     */
	@TableField("user_id")
	private Long userId;
    /**
     * 代理用户id
     */
	@TableField("agent_user_id")
	private Long agentUserId;
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getAgentUserId() {
		return agentUserId;
	}

	public void setAgentUserId(Long agentUserId) {
		this.agentUserId = agentUserId;
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

	public static final String USER_ID = "user_id";

	public static final String AGENT_USER_ID = "agent_user_id";

	public static final String CREATE_TIME = "create_time";

	public static final String UPDATE_TIME = "update_time";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "UserAgent{" +
			"id=" + id +
			", userId=" + userId +
			", agentUserId=" + agentUserId +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}
