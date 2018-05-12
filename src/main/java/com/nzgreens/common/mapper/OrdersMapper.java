package com.nzgreens.common.mapper;

import com.nzgreens.common.entity.Orders;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.nzgreens.common.entity.extend.ManageItemDTO;
import com.nzgreens.common.entity.extend.OrderItemDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
  * 订单表 Mapper 接口
 * </p>
 *
 * @author sylar
 * @since 2018-04-05
 */
public interface OrdersMapper extends BaseMapper<Orders> {

    /**
     * 通过orderNumber查询order item list
     * @param orderNumber
     * @return
     */
    List<OrderItemDTO> selectOrderItems(@Param("orderNumber") String orderNumber);


    /**
     * 通过orderNumber查询 manage item list
     * @param orderNumber
     * @return
     */
    List<ManageItemDTO> selectManageItems(@Param("orderNumber") String orderNumber);
}