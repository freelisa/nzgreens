package com.nzgreens.common.mapper;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.nzgreens.common.entity.ShoppingCart;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.nzgreens.common.entity.UserAgent;
import com.nzgreens.common.entity.extend.UserShoppingCartDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
  * 购物车 Mapper 接口
 * </p>
 *
 * @author sylar
 * @since 2018-04-05
 */
public interface ShoppingCartMapper extends BaseMapper<ShoppingCart> {

    /**
     * 获取代理用户购物车列表
     * @param agentId
     * @param page
     * @return
     */
    List<UserShoppingCartDTO> selectAgentShoppingCart(@Param("agentId") Long agentId, Pagination page);

    /**
     * 获取用户购物车列表
     * @param userAgent
     *@param page  @return
     */
    List<UserShoppingCartDTO> selectShoppingCart(UserAgent userAgent, Pagination page);
}