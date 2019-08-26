package com.nzgreens.common.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nzgreens.common.entity.PayOrder;
import com.nzgreens.common.entity.TripartiteOrder;
import com.nzgreens.common.mapper.PayOrderMapper;
import com.nzgreens.common.service.PayOrderService;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PayOrderServiceImpl
        extends ServiceImpl<PayOrderMapper, PayOrder>
        implements PayOrderService
{
    private static final Logger logger = LoggerFactory.getLogger(PayOrderServiceImpl.class);

    public void notifyBusiness(PayOrder payOrder, String tripartiteOrderNo)
    {
        try
        {
            payOrder.setUpdateTime(new Date());
            updateById(payOrder);

            TripartiteOrder tOrder = new TripartiteOrder();
            tOrder.setPayState(payOrder.getPayState());
            tOrder.setOrderId(payOrder.getId());
            tOrder.setTripartiteOrderId(tripartiteOrderNo);
            tOrder.setCreateTime(new Date());
            tOrder.insert();
        }
        catch (Exception e)
        {
            logger.error("������������������----------------------------------------->,{}", payOrder.getId());
        }
    }
}
