package com.nzgreens.common.entity.extend;

/**
 * Created by sylar on 2018/5/7.
 */
public class OrderItemDTO extends BaseOrderItem{
    private Integer itemStatus;
    private Integer commentStatus;
    private Boolean commentShow;

    public Integer getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(Integer itemStatus) {
        this.itemStatus = itemStatus;
    }

    public Integer getCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(Integer commentStatus) {
        this.commentStatus = commentStatus;
    }

    public Boolean getCommentShow() {
        return commentShow;
    }

    public void setCommentShow(Boolean commentShow) {
        this.commentShow = commentShow;
    }
}
