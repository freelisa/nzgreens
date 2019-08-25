package com.nzgreens.common.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.nzgreens.common.entity.Orders;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.nzgreens.common.entity.extend.ManageItemDTO;
import com.nzgreens.common.entity.extend.MergeOrderItem;
import com.nzgreens.common.entity.extend.OrderItemDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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
     * @param map
     * @return
     */
    List<OrderItemDTO> selectOrderItems(@Param("requestMap") Map<String,Object> map);


    /**
     * 通过orderNumber查询 manage item list
     * @param map
     * @return
     */
    List<ManageItemDTO> selectManageItems(@Param("requestMap") Map<String, Object> map);

    /**
     * 查询用户合并管理列表
     * @param map
     * @param userAgentId
     *@param page  @return
     */
    List<MergeOrderItem> selectMergeManageItemForPage(@Param("requestMap") Map<String,Object> map, Long userAgentId, Page page);
}