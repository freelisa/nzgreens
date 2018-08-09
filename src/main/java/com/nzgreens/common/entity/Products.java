package com.nzgreens.common.entity;

import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 商品表
 * </p>
 *
 * @author sylar
 * @since 2018-04-06
 */
public class Products extends Model<Products> {

    private static final long serialVersionUID = 1L;

    /**
     * 商品id
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * title
     */
	private String title;
    /**
     * 品牌id
     */
	@TableField("brand_id")
	private Long brandId;
    /**
     * 商品重量(单位：克)
     */
	private Long weight;
    /**
     * 商品图片
     */
	private String image;
    /**
     * 原价
     */
	@TableField("cost_price")
	private Long costPrice;
    /**
     * 售价
     */
	@TableField("selling_price")
	private Long sellingPrice;
    /**
     * 分类id
     */
	@TableField("category_id")
	private Long categoryId;
    /**
     * 商品详情
     */
	private String detail;
    /**
     * 库存
     */
	private Long stock;
    /**
     * 商品评分
     */
	private BigDecimal score;
    /**
     * 销量
     */
	@TableField("sales_volume")
	private Long salesVolume;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getBrandId() {
		return brandId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	public Long getWeight() {
		return weight;
	}

	public void setWeight(Long weight) {
		this.weight = weight;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Long getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(Long costPrice) {
		this.costPrice = costPrice;
	}

	public Long getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(Long sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Long getStock() {
		return stock;
	}

	public void setStock(Long stock) {
		this.stock = stock;
	}

	public BigDecimal getScore() {
		return score;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}

	public Long getSalesVolume() {
		return salesVolume;
	}

	public void setSalesVolume(Long salesVolume) {
		this.salesVolume = salesVolume;
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

	public static final String TITLE = "title";

	public static final String BRAND_ID = "brand_id";

	public static final String WEIGHT = "weight";

	public static final String IMAGE = "image";

	public static final String COST_PRICE = "cost_price";

	public static final String SELLING_PRICE = "selling_price";

	public static final String CATEGORY_ID = "category_id";

	public static final String DETAIL = "detail";

	public static final String STOCK = "stock";

	public static final String SCORE = "score";

	public static final String SALES_VOLUME = "sales_volume";

	public static final String IS_VALID = "is_valid";

	public static final String CREATE_TIME = "create_time";

	public static final String UPDATE_TIME = "update_time";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Products{" +
			"id=" + id +
			", title=" + title +
			", brandId=" + brandId +
			", weight=" + weight +
			", image=" + image +
			", costPrice=" + costPrice +
			", sellingPrice=" + sellingPrice +
			", categoryId=" + categoryId +
			", detail=" + detail +
			", stock=" + stock +
			", score=" + score +
			", salesVolume=" + salesVolume +
			", isValid=" + isValid +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}
