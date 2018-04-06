package com.nzgreens.common.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 商品评价表
 * </p>
 *
 * @author sylar
 * @since 2018-04-06
 */
@TableName("product_comment")
public class ProductComment extends Model<ProductComment> {

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
     * 商品id
     */
	@TableField("product_id")
	private Long productId;
    /**
     * 商品评价
     */
	private String comment;
    /**
     * 商品评分
     */
	private BigDecimal score;
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

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public BigDecimal getScore() {
		return score;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
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

	public static final String PRODUCT_ID = "product_id";

	public static final String COMMENT = "comment";

	public static final String SCORE = "score";

	public static final String CREATE_TIME = "create_time";

	public static final String UPDATE_TIME = "update_time";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "ProductComment{" +
			"id=" + id +
			", userId=" + userId +
			", productId=" + productId +
			", comment=" + comment +
			", score=" + score +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}
