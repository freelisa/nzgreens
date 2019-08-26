package com.nzgreens.common.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import java.io.Serializable;
import java.util.Date;

@TableName("pay_order")
public class PayOrder extends Model<PayOrder> {
    private static final long serialVersionUID = 1L;
    @TableId(value="id", type=IdType.AUTO)
    private Integer id;
    @TableField("user_id")
    private Long userId;
    @TableField("shop_id")
    private Long shopId;
    @TableField("channel_id")
    private Integer channelId;
    private Integer price;
    @TableField("pay_state")
    private Integer payState;
    @TableField("is_valid")
    private Integer isValid;
    @TableField("create_time")
    private Date createTime;
    @TableField("update_time")
    private Date updateTime;
    public static final String ID = "id";
    public static final String USER_ID = "user_id";
    public static final String SHOP_ID = "shop_id";
    public static final String CHANNEL_ID = "channel_id";
    public static final String PRICE = "price";
    public static final String PAY_STATE = "pay_state";
    public static final String IS_VALID = "is_valid";
    public static final String CREATE_TIME = "create_time";
    public static final String UPDATE_TIME = "update_time";

    public Integer getId()
    {
        return this.id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Long getUserId()
    {
        return this.userId;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getShopId()
    {
        return this.shopId;
    }

    public void setShopId(Long shopId)
    {
        this.shopId = shopId;
    }

    public Integer getChannelId()
    {
        return this.channelId;
    }

    public void setChannelId(Integer channelId)
    {
        this.channelId = channelId;
    }

    public Integer getPrice()
    {
        return this.price;
    }

    public void setPrice(Integer price)
    {
        this.price = price;
    }

    public Integer getPayState()
    {
        return this.payState;
    }

    public void setPayState(Integer payState)
    {
        this.payState = payState;
    }

    public Integer getIsValid()
    {
        return this.isValid;
    }

    public void setIsValid(Integer isValid)
    {
        this.isValid = isValid;
    }

    public Date getCreateTime()
    {
        return this.createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public Date getUpdateTime()
    {
        return this.updateTime;
    }

    public void setUpdateTime(Date updateTime)
    {
        this.updateTime = updateTime;
    }

    @Override
    protected Serializable pkVal()
    {
        return this.id;
    }

    @Override
    public String toString() {
        return "PayOrder{id=" + this.id + ", userId=" + this.userId + ", shopId=" + this.shopId + ", channelId=" + this.channelId + ", price=" + this.price + ", payState=" + this.payState + ", isValid=" + this.isValid + ", createTime=" + this.createTime + ", updateTime=" + this.updateTime + "}";
    }
}
