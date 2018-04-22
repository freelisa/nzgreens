package com.nzgreens.common.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.nzgreens.common.entity.UserOrder;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.nzgreens.common.entity.extend.UserOrderRequest;

import java.util.List;

/**
 * <p>
  * 用户订单关联表 Mapper 接口
 * </p>
 *
 * @author sylar
 * @since 2018-04-05
 */
public interface UserOrderMapper extends BaseMapper<UserOrder> {

    /**
     * 搜索普通用户订单列表
     * @param page
     * @param orderRequest
     * @return
     */
    List selectUserOrderList(Page page, UserOrderRequest orderRequest);
}