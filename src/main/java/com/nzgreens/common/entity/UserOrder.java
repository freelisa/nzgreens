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
 * 用户订单关联表
 * </p>
 *
 * @author sylar
 * @since 2018-04-05
 */
@TableName("user_order")
public class UserOrder extends Model<UserOrder> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 用户id
     */
	@TableField("user_id")
	private Long userId;
    /**
     * 订单号
     */
	@TableField("order_number")
	private String orderNumber;
    /**
     * 收货方式(1:自收/购买 2:代收/合并)
     */
	@TableField("delivery_mode")
	private Integer deliveryMode;
    /**
     * 收货地址id
     */
	@TableField("address_id")
	private Long addressId;
    /**
     * 商品总金额（单位：分）
     */
	@TableField("product_price")
	private Long productPrice;
    /**
     * 订单运费（单位：分）
     */
	private Long freight;
    /**
     * 订单总金额（单位：分）
     */
	private Long price;
    /**
     * 订单类型（1:代理处理 2：系统处理）
     */
	private Integer type;
    /**
     * 订单状态(-1：拒绝 0:未处理 1:已处理 2:已上传运单凭证)
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


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Integer getDeliveryMode() {
		return deliveryMode;
	}

	public void setDeliveryMode(Integer deliveryMode) {
		this.deliveryMode = deliveryMode;
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public Long getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Long productPrice) {
		this.productPrice = productPrice;
	}

	public Long getFreight() {
		return freight;
	}

	public void setFreight(Long freight) {
		this.freight = freight;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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

	public static final String USER_ID = "user_id";

	public static final String ORDER_NUMBER = "order_number";

	public static final String DELIVERY_MODE = "delivery_mode";

	public static final String ADDRESS_ID = "address_id";

	public static final String PRODUCT_PRICE = "product_price";

	public static final String FREIGHT = "freight";

	public static final String PRICE = "price";

	public static final String TYPE = "type";

	public static final String STATUS = "status";

	public static final String CREATE_TIME = "create_time";

	public static final String UPDATE_TIME = "update_time";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "UserOrder{" +
			"id=" + id +
			", userId=" + userId +
			", orderNumber=" + orderNumber +
			", deliveryMode=" + deliveryMode +
			", addressId=" + addressId +
			", productPrice=" + productPrice +
			", freight=" + freight +
			", price=" + price +
			", type=" + type +
			", status=" + status +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}
