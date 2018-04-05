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
 * 用户收货地址
 * </p>
 *
 * @author sylar
 * @since 2018-04-05
 */
@TableName("user_address")
public class UserAddress extends Model<UserAddress> {

    private static final long serialVersionUID = 1L;

    /**
     * 收货地址id
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 收货地址
     */
	private String address;
    /**
     * 联系人
     */
	private String contact;
    /**
     * 联系电话
     */
	private String telephone;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
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

	public static final String ADDRESS = "address";

	public static final String CONTACT = "contact";

	public static final String TELEPHONE = "telephone";

	public static final String IS_VALID = "is_valid";

	public static final String CREATE_TIME = "create_time";

	public static final String UPDATE_TIME = "update_time";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "UserAddress{" +
			"id=" + id +
			", address=" + address +
			", contact=" + contact +
			", telephone=" + telephone +
			", isValid=" + isValid +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}
