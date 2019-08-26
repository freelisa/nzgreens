package com.nzgreens.common.service;

import com.nzgreens.common.common.result.BaseResponse;
import com.nzgreens.common.entity.PayOrder;

/**
 * 支付接口
 * 支付方式 1：微信支付 2：IOS原生支付
 */
public interface PayService {
	/**
	 * 创建支付订单
	 * @param payOrder
	 * @return
	 */
	Integer createOrder(PayOrder payOrder);

	/**
	 * 查询订单
	 * @param orderId
	 * @return
	 */
	BaseResponse queryOrder(Integer orderId);
}
