package com.nzgreens.common.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nzgreens.common.common.result.BaseResponse;
import com.nzgreens.common.common.utils.TreeObject;
import com.nzgreens.common.entity.SysResource;
import com.nzgreens.common.mapper.SysResourceMapper;
import com.nzgreens.common.mapper.SysUserMapper;
import com.nzgreens.common.service.SysResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author helizheng
 * @since 2017-07-15
 */
@Service
public class SysResourceServiceImpl extends ServiceImpl<SysResourceMapper, SysResource> implements SysResourceService {
    public static Logger log = LoggerFactory.getLogger(SysResourceServiceImpl.class);
    @Autowired
    private SysResourceMapper sysResourceMapper;
    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 查询资源列表
     *
     * @param sysResource
     * @return
     */
    public BaseResponse<List<TreeObject>> queryResource(SysResource sysResource) throws Exception {
        BaseResponse<List<TreeObject>> resd = new BaseResponse<>();
        List<TreeObject> list = sysUserMapper.queryResource(sysResource);
        resd.setData(list);
        return resd;
    }

    /**
     * 删除菜单资源
     *
     * @param id
     * @return
     */
    public BaseResponse<Boolean> deleteResource(Integer id) throws Exception {
        sysResourceMapper.deleteById(id);
        return new BaseResponse<>();
    }

    /**
     * 修改菜单资源
     *
     * @param sysResource
     * @return
     */
    public BaseResponse<Boolean> updateResource(SysResource sysResource) throws Exception {
        BaseResponse<Boolean> resd = new BaseResponse<>();
        sysResourceMapper.updateById(sysResource);
        return resd;
    }

    /**
     * 新增菜单资源
     *
     * @param sysResource
     * @return
     */
    public BaseResponse<Long> saveResource(SysResource sysResource) throws Exception {
        sysResourceMapper.insert(sysResource);
        return new BaseResponse<>();
    }

    /**
     * 根据用户ID查询该用户权限菜单
     *
     * @param userId
     * @return
     */
    public BaseResponse<List<TreeObject>> queryUserResourceById(Integer userId) throws Exception {
        BaseResponse<List<TreeObject>> resd = new BaseResponse<>();
        List<TreeObject> list = sysUserMapper.queryUserResourceById(userId);
        resd.setData(list);
        return resd;
    }

    /**
     * 根据资源ID查询资源信息
     *
     * @param id
     * @return
     */
    public BaseResponse<SysResource> queryResourceById(Integer id) throws Exception {
        BaseResponse<SysResource> resd = new BaseResponse<>();
        SysResource sysResource = sysResourceMapper.selectById(id);
        resd.setData(sysResource);
        return resd;
    }

    /**
     * 根据角色ID查询权限菜单
     *
     * @param roleId
     * @return
     */
    public BaseResponse<List<TreeObject>> queryRoleResourceById(Integer roleId) throws Exception {
        BaseResponse<List<TreeObject>> resd = new BaseResponse<>();
        List<TreeObject> list = sysUserMapper.queryRoleResourceById(roleId);
        resd.setData(list);
        return resd;
    }
}
