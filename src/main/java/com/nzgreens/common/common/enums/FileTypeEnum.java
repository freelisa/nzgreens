package com.nzgreens.common.common.enums;

/**
 * 上传文件类型枚举
 * Created by sylar on 2018/4/5.
 * @author sylar
 */
public enum  FileTypeEnum {
    _USER("user","用户"),
    _PRODUCTS("product","商品"),
    _FREIGHT("freight","订单物流凭证");

    private String path;
    private String description;

    FileTypeEnum(String path, String description) {
        this.path = path;
        this.description = description;
    }

    public String getPath() {
        return path;
    }

    public String getDescription() {
        return description;
    }
}
