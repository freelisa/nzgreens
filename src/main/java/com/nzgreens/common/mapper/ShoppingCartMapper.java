package com.nzgreens.common.mapper;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.nzgreens.common.entity.ShoppingCart;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.nzgreens.common.entity.extend.ShoppingCartDTO;

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

    List<ShoppingCartDTO> selectShoppingCart(Pagination page);
}