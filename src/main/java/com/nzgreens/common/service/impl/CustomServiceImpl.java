package com.nzgreens.common.service.impl;

import com.nzgreens.common.entity.ProductsCrawl;
import com.nzgreens.common.mapper.CustomMapper;
import com.nzgreens.common.service.CustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:helizheng
 * @Date: Created in 2017/8/6 18:07
 */
@Service
public class CustomServiceImpl implements CustomService {
    @Autowired
    private CustomMapper customMapper;

    /**
     * 查询抓取的数据是否已经添加到产品表
     * @return
     */
    @Override
    public List<ProductsCrawl> queryProductIsExists() {
        return customMapper.queryProductIsExists();
    }
}
