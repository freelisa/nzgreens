package com.nzgreens.common.common.result;

import java.io.Serializable;

/**
 * 基础返回类
 * Created by sylar on 2017/7/13.
 * @author sylar
 */
public class BaseResponse<T> implements Serializable {
    private boolean success;
    private String code;
    private String msg;
    private T data;

    public BaseResponse() {
    }

    public BaseResponse(boolean success) {
        this.success = success;
    }

    public BaseResponse(boolean success, String code, String msg) {
        this.success = success;
        this.code = code;
        this.msg = msg;
    }

    public BaseResponse(boolean success, String code, String msg, T data) {
        this.success = success;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
