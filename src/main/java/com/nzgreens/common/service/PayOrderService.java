package com.nzgreens.common.service;

import com.baomidou.mybatisplus.service.IService;
import com.nzgreens.common.entity.PayOrder;

public abstract interface PayOrderService
        extends IService<PayOrder>
{
    public abstract void notifyBusiness(PayOrder paramPayOrder, String paramString);
}
