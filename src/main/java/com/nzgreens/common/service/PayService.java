package com.nzgreens.common.service;

import com.nzgreens.common.common.result.BaseResponse;
import com.nzgreens.common.entity.PayOrder;

/**
 * @author sylar
 * @Description:
 * @date 2018/10/2 6:47 PM
 */
public interface PayService {
    public abstract Integer createOrder(PayOrder paramPayOrder);

    public abstract BaseResponse queryOrder(Integer paramInteger);
}
