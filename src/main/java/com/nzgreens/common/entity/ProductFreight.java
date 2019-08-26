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
 * 商品运费设置表
 * </p>
 *
 * @author sylar
 * @since 2018-04-06
 */
@TableName("product_freight")
public class ProductFreight extends Model<ProductFreight> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 商品重量（单位：克）
     */
	@TableField("product_weight")
	private Long productWeight;
    /**
     * 运费（单位：分）
     */
	private Long freight;
	@TableField("min_freight")
	private Long minFreight;
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

	public Long getProductWeight() {
		return productWeight;
	}

	public void setProductWeight(Long productWeight) {
		this.productWeight = productWeight;
	}

	public Long getFreight() {
		return freight;
	}

	public void setFreight(Long freight) {
		this.freight = freight;
	}

	public Long getMinFreight() {
		return this.minFreight;
	}

	public void setMinFreight(Long minFreight) {
		this.minFreight = minFreight;
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

	public static final String PRODUCT_WEIGHT = "product_weight";

	public static final String FREIGHT = "freight";

	public static final String CREATE_TIME = "create_time";

	public static final String UPDATE_TIME = "update_time";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "ProductFreight{" +
			"id=" + id +
			", productWeight=" + productWeight +
			", freight=" + freight +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}
