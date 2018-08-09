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
 * 订单表
 * </p>
 *
 * @author sylar
 * @since 2018-05-12
 */
public class Orders extends Model<Orders> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 订单id（用于合并关联）
     */
	@TableField("order_id")
	private Long orderId;
    /**
     * 订单号
     */
	@TableField("order_number")
	private String orderNumber;
    /**
     * 商品id
     */
	@TableField("product_id")
	private Long productId;
    /**
     * 商品数量
     */
	@TableField("product_number")
	private Long productNumber;
    /**
     * 商品价格（单位：分）
     */
	private Long price;
    /**
     * 代理购买金额（单位：分）
     */
	@TableField("agent_price")
	private Long agentPrice;
    /**
     * 状态（0：未处理 1：已处理）
     */
	private Integer status;
    /**
     * 评价状态（0：未评价 1：已评价）
     */
	@TableField("comment_status")
	private Integer commentStatus;
    /**
     * 是否有效(1:有效 0:无效)
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

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(Long productNumber) {
		this.productNumber = productNumber;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Long getAgentPrice() {
		return agentPrice;
	}

	public void setAgentPrice(Long agentPrice) {
		this.agentPrice = agentPrice;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getCommentStatus() {
		return commentStatus;
	}

	public void setCommentStatus(Integer commentStatus) {
		this.commentStatus = commentStatus;
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

	public static final String ORDER_NUMBER = "order_number";

	public static final String PRODUCT_ID = "product_id";

	public static final String PRODUCT_NUMBER = "product_number";

	public static final String PRICE = "price";

	public static final String AGENT_PRICE = "agent_price";

	public static final String STATUS = "status";

	public static final String COMMENT_STATUS = "comment_status";

	public static final String IS_VALID = "is_valid";

	public static final String CREATE_TIME = "create_time";

	public static final String UPDATE_TIME = "update_time";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Orders{" +
			"id=" + id +
			", orderId=" + orderId +
			", orderNumber=" + orderNumber +
			", productId=" + productId +
			", productNumber=" + productNumber +
			", price=" + price +
			", agentPrice=" + agentPrice +
			", status=" + status +
			", commentStatus=" + commentStatus +
			", isValid=" + isValid +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}
