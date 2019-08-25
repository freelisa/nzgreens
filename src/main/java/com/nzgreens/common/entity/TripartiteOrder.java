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
 * 三方支付订单表
 * </p>
 *
 * @author sylar
 * @since 2018-07-01
 */
@TableName("tripartite_order")
public class TripartiteOrder extends Model<TripartiteOrder> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 订单ID
     */
	@TableField("order_id")
	private Integer orderId;
    /**
     * 三方订单
     */
	@TableField("tripartite_order_id")
	private String tripartiteOrderId;
    /**
     * 三方订单状态（0：支付中 1：支付成功 -1：支付失败 ）
     */
	@TableField("pay_state")
	private Integer payState;
    /**
     * 是否有效（1：有效 0：无效）
     */
	@TableField("is_valid")
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


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getTripartiteOrderId() {
		return tripartiteOrderId;
	}

	public void setTripartiteOrderId(String tripartiteOrderId) {
		this.tripartiteOrderId = tripartiteOrderId;
	}

	public Integer getPayState() {
		return payState;
	}

	public void setPayState(Integer payState) {
		this.payState = payState;
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

	public static final String ORDER_ID = "order_id";

	public static final String TRIPARTITE_ORDER_ID = "tripartite_order_id";

	public static final String PAY_STATE = "pay_state";

	public static final String IS_VALID = "is_valid";

	public static final String CREATE_TIME = "create_time";

	public static final String UPDATE_TIME = "update_time";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "TripartiteOrder{" +
			"id=" + id +
			", orderId=" + orderId +
			", tripartiteOrderId=" + tripartiteOrderId +
			", payState=" + payState +
			", isValid=" + isValid +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}
