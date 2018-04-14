package com.nzgreens.common.mapper;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.nzgreens.common.entity.Products;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.nzgreens.common.entity.extend.ProductFuzzyDTO;
import com.nzgreens.common.entity.extend.UserProductItemDTO;
import com.nzgreens.common.entity.extend.UserShoppingCartDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
  * 商品表 Mapper 接口
 * </p>
 *
 * @author sylar
 * @since 2018-04-05
 */
public interface ProductsMapper extends BaseMapper<Products> {

    /**
     * 通过关键字模糊查询商品
     * @param productFuzzyWord
     * @return
     */
    List<ProductFuzzyDTO> fuzzySearch(@Param("productFuzzyWord") String productFuzzyWord);


    /**
     * 获取用户商品
     * @param agentUserId
     * @param page
     * @return
     */
    List<UserProductItemDTO> selectUserProducts(@Param("agentUserId") Long agentUserId, Pagination page);
}