package com.nzgreens.common.service.impl;

import com.nzgreens.common.common.result.BaseResponse;
import com.nzgreens.common.entity.PayOrder;
import com.nzgreens.common.service.PayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.stereotype.Service;

/**
 * @Author:helizheng
 * @Date: Created in 2017/8/21 23:21
 */
@Service("iosPayService")
public class IosPayServiceImpl implements PayService {
    private final static Logger logger = LoggerFactory.getLogger(IosPayServiceImpl.class);

    /**
     * 创建支付订单
     *
     * @param payOrder
     * @return
     */
    @Override
    public Integer createOrder(PayOrder payOrder) {
        logger.info("ios创建订单开始-------------------->");
        //请求支付接口
        PayOrder payOrd = new PayOrder();
        //保存订单
        try {
            BeanUtils.copyProperties(payOrder, payOrd);
            //新增订单
            payOrd.insert();
        } catch (BeansException e) {
            logger.error("ios创建订单失败-------------------->");
            return 0;
        }
        //将返回的数据返回给app
        logger.info("ios创建订单结束-------------------->");
        return payOrd.getId();
    }

    /**
     * 查询订单
     *
     * @param orderId
     * @return
     */
    @Override
    public BaseResponse queryOrder(Integer orderId) {
        return null;
    }
}
