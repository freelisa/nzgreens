package com.nzgreens.common.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import java.io.Serializable;
import java.util.Date;

@TableName("tripartite_order")
public class TripartiteOrder
        extends Model<TripartiteOrder>
{
    private static final long serialVersionUID = 1L;
    @TableId(value="id", type=IdType.AUTO)
    private Integer id;
    @TableField("order_id")
    private Integer orderId;
    @TableField("tripartite_order_id")
    private String tripartiteOrderId;
    @TableField("pay_state")
    private Integer payState;
    @TableField("is_valid")
    private Integer isValid;
    @TableField("create_time")
    private Date createTime;
    @TableField("update_time")
    private Date updateTime;
    public static final String ID = "id";
    public static final String ORDER_ID = "order_id";
    public static final String TRIPARTITE_ORDER_ID = "tripartite_order_id";
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

    public Integer getOrderId()
    {
        return this.orderId;
    }

    public void setOrderId(Integer orderId)
    {
        this.orderId = orderId;
    }

    public String getTripartiteOrderId()
    {
        return this.tripartiteOrderId;
    }

    public void setTripartiteOrderId(String tripartiteOrderId)
    {
        this.tripartiteOrderId = tripartiteOrderId;
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
    public String toString()
    {
        return "TripartiteOrder{id=" + this.id + ", orderId=" + this.orderId + ", tripartiteOrderId=" + this.tripartiteOrderId + ", payState=" + this.payState + ", isValid=" + this.isValid + ", createTime=" + this.createTime + ", updateTime=" + this.updateTime + "}";
    }
}
