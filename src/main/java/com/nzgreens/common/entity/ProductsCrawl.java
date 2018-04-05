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
 * 商品爬取记录表
 * </p>
 *
 * @author sylar
 * @since 2018-04-05
 */
@TableName("products_crawl")
public class ProductsCrawl extends Model<ProductsCrawl> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 商品id
     */
	@TableField("product_id")
	private Long productId;
    /**
     * 爬取的商品id
     */
	@TableField("reptile_product_id")
	private String reptileProductId;
    /**
     * 品牌
     */
	private String brand;
    /**
     * title
     */
	private String title;
    /**
     * 重量
     */
	private String weight;
    /**
     * 商品图片
     */
	private String image;
    /**
     * 售价
     */
	@TableField("selling_price")
	private Long sellingPrice;
    /**
     * 分类(37,42)
     */
	private String category;
    /**
     * 分类名称（母婴,幼儿）
     */
	@TableField("category_name")
	private String categoryName;
    /**
     * 商品详情
     */
	private String detail;
    /**
     * 数据状态(0:未处理 1:已处理)
     */
	private Integer state;
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

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getReptileProductId() {
		return reptileProductId;
	}

	public void setReptileProductId(String reptileProductId) {
		this.reptileProductId = reptileProductId;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Long getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(Long sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
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

	public static final String PRODUCT_ID = "product_id";

	public static final String REPTILE_PRODUCT_ID = "reptile_product_id";

	public static final String BRAND = "brand";

	public static final String TITLE = "title";

	public static final String WEIGHT = "weight";

	public static final String IMAGE = "image";

	public static final String SELLING_PRICE = "selling_price";

	public static final String CATEGORY = "category";

	public static final String CATEGORY_NAME = "category_name";

	public static final String DETAIL = "detail";

	public static final String STATE = "state";

	public static final String IS_VALID = "is_valid";

	public static final String CREATE_TIME = "create_time";

	public static final String UPDATE_TIME = "update_time";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "ProductsCrawl{" +
			"id=" + id +
			", productId=" + productId +
			", reptileProductId=" + reptileProductId +
			", brand=" + brand +
			", title=" + title +
			", weight=" + weight +
			", image=" + image +
			", sellingPrice=" + sellingPrice +
			", category=" + category +
			", categoryName=" + categoryName +
			", detail=" + detail +
			", state=" + state +
			", isValid=" + isValid +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}
