package com.nzgreens.common.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nzgreens.common.entity.TripartiteOrder;
import com.nzgreens.common.mapper.TripartiteOrderMapper;
import com.nzgreens.common.service.TripartiteOrderService;
import org.springframework.stereotype.Service;

@Service
public class TripartiteOrderServiceImpl
        extends ServiceImpl<TripartiteOrderMapper, TripartiteOrder>
        implements TripartiteOrderService
{}
