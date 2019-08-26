package com.nzgreens.common.service.impl;

import com.nzgreens.common.common.result.BaseResponse;
import com.nzgreens.common.entity.PayOrder;
import com.nzgreens.common.service.PayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.stereotype.Service;

@Service("iosPayService")
public class IosPayServiceImpl
        implements PayService
{
    private static final Logger logger = LoggerFactory.getLogger(IosPayServiceImpl.class);

    public Integer createOrder(PayOrder payOrder)
    {
        logger.info("ios������������������-------------------->");

        PayOrder payOrd = new PayOrder();
        try
        {
            BeanUtils.copyProperties(payOrder, payOrd);

            payOrd.insert();
        }
        catch (BeansException e)
        {
            logger.error("ios������������������-------------------->");
            return Integer.valueOf(0);
        }
        logger.info("ios������������������-------------------->");
        return payOrd.getId();
    }

    public BaseResponse queryOrder(Integer orderId)
    {
        return null;
    }
}
