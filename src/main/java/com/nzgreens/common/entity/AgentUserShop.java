package com.nzgreens.common.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 代理商店表
 * </p>
 *
 * @author sylar
 * @since 2018-04-06
 */
@TableName("agent_user_shop")
public class AgentUserShop extends Model<AgentUserShop> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
	@TableId(value="id", type= IdType.AUTO)
	@JSONField(serialize = false)
	private Long id;
    /**
     * 代理用户id
     */
	@TableField("agent_user_id")
	@JSONField(serialize = false)
	private Long agentUserId;
    /**
     * 商店名称
     */
	@TableField("shop_name")
	private String shopName;
    /**
     * 商店图片
     */
	@TableField("shop_image")
	private String shopImage;
    /**
     * 创建时间
     */
	@TableField("create_time")
	@JSONField(serialize = false)
	private Date createTime;
    /**
     * 更新时间
     */
	@TableField("update_time")
	@JSONField(serialize = false)
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

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopImage() {
		return shopImage;
	}

	public void setShopImage(String shopImage) {
		this.shopImage = shopImage;
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

	public static final String SHOP_NAME = "shop_name";

	public static final String SHOP_IMAGE = "shop_image";

	public static final String CREATE_TIME = "create_time";

	public static final String UPDATE_TIME = "update_time";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "AgentUserShop{" +
			"id=" + id +
			", agentUserId=" + agentUserId +
			", shopName=" + shopName +
			", shopImage=" + shopImage +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}
