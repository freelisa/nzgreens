package com.nzgreens.common.service.impl;

import com.nzgreens.common.entity.PayOrder;
import com.nzgreens.common.entity.TripartiteOrder;
import com.nzgreens.common.mapper.PayOrderMapper;
import com.nzgreens.common.service.PayOrderService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 支付订单表 服务实现类
 * </p>
 *
 * @author sylar
 * @since 2018-07-01
 */
@Service
public class PayOrderServiceImpl extends ServiceImpl<PayOrderMapper, PayOrder> implements PayOrderService {
    private static final Logger logger = LoggerFactory.getLogger(PayOrderServiceImpl.class);
    @Override
    public void notifyBusiness(PayOrder payOrder, String tripartiteOrderNo) {
        try {
            //修改订单表状态
            payOrder.setUpdateTime(new Date());
            this.updateById(payOrder);
            //新增三方订单表
            TripartiteOrder tOrder = new TripartiteOrder();
            tOrder.setPayState(payOrder.getPayState());
            tOrder.setOrderId(payOrder.getId());
            tOrder.setTripartiteOrderId(tripartiteOrderNo);
            tOrder.setCreateTime(new Date());
            tOrder.insert();
        } catch (Exception e) {
            logger.error("处理订单失败----------------------------------------->,{}",payOrder.getId());
        }
    }
}
