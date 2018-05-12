package com.nzgreens.common.entity.extend;

/**
 * Created by sylar on 2018/5/7.
 */
public class ManageItemDTO extends BaseOrderItem{
    private Boolean canMerge;

    public Boolean getCanMerge() {
        return canMerge;
    }

    public void setCanMerge(Boolean canMerge) {
        this.canMerge = canMerge;
    }
}
