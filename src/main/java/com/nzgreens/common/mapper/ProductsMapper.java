package com.nzgreens.common.mapper;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.nzgreens.common.entity.Products;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.nzgreens.common.entity.extend.UserProductDetailRequest;
import com.nzgreens.common.entity.extend.ProductDetailDTO;
import com.nzgreens.common.entity.extend.ProductFuzzyDTO;
import com.nzgreens.common.entity.extend.UserProductDetailDTO;
import com.nzgreens.common.entity.extend.UserProductItemDTO;
import com.nzgreens.common.entity.service.ProductPrice;
import com.nzgreens.common.entity.service.UserProductPriceSearch;
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

    /**
     * 代理用户商品详情
     * @param productId
     * @return
     */
    ProductDetailDTO selectProductDetail(@Param("productId") Long productId);

    /**
     * 用户商品详情
     * @param detailRequest
     * @return
     */
    UserProductDetailDTO selectUserProductDetail(UserProductDetailRequest detailRequest);

    /**
     * 用户商品价格
     * @param priceSearch
     * @return
     */
    List<ProductPrice> selectUserProductPrice(UserProductPriceSearch priceSearch);
}