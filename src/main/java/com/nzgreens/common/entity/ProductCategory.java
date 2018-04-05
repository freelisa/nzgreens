package com.nzgreens.common.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 商品分类表
 * </p>
 *
 * @author sylar
 * @since 2018-04-05
 */
@TableName("product_category")
public class ProductCategory extends Model<ProductCategory> {

    private static final long serialVersionUID = 1L;

    /**
     * 分类id
     */
	private Long id;
    /**
     * 父类id
     */
	@TableField("parent_id")
	private Long parentId;
    /**
     * 分类名称
     */
	private String name;
    /**
     * 是否有效(1:有效 0:无效)
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


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public static final String PARENT_ID = "parent_id";

	public static final String NAME = "name";

	public static final String IS_VALID = "is_valid";

	public static final String CREATE_TIME = "create_time";

	public static final String UPDATE_TIME = "update_time";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "ProductCategory{" +
			"id=" + id +
			", parentId=" + parentId +
			", name=" + name +
			", isValid=" + isValid +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}
