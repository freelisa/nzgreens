package com.nzgreens.common.service;

import com.nzgreens.common.entity.PayOrder;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 支付订单表 服务类
 * </p>
 *
 * @author sylar
 * @since 2018-07-01
 */
public interface PayOrderService extends IService<PayOrder> {
    /**
     * 修改订单表状态 新增三方表订单
     * @param payOrder
     */
    void notifyBusiness(PayOrder payOrder,String tripartiteOrderNo);
}
