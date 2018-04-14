package com.nzgreens.common.service;

import com.baomidou.mybatisplus.service.IService;
import com.nzgreens.common.common.result.BaseResponse;
import com.nzgreens.common.common.utils.TreeObject;
import com.nzgreens.common.entity.SysResource;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author helizheng
 * @since 2017-07-15
 */
public interface SysResourceService extends IService<SysResource> {
    /**
     * 查询资源列表
     * @param sysResource
     * @return
     */
    BaseResponse<List<TreeObject>> queryResource(SysResource sysResource) throws Exception;

    /**
     * 删除菜单资源
     * @param id
     * @return
     */
    BaseResponse<Boolean> deleteResource(Integer id) throws Exception;

    /**
     * 修改菜单资源
     * @param sysResource
     * @return
     */
    BaseResponse<Boolean> updateResource(SysResource sysResource) throws Exception;

    /**
     * 新增菜单资源
     * @param sysResource
     * @return
     */
    BaseResponse<Long> saveResource(SysResource sysResource) throws Exception;

    /**
     * 根据用户ID查询该用户权限菜单
     * @param userId
     * @return
     */
    BaseResponse<List<TreeObject>> queryUserResourceById(Integer userId) throws Exception;

    /**
     * 根据资源ID查询资源信息
     * @param id
     * @return
     */
    BaseResponse<SysResource> queryResourceById(Integer id) throws Exception;

    /**
     * 根据角色ID查询权限菜单
     * @param roleId
     * @return
     */
    BaseResponse<List<TreeObject>> queryRoleResourceById(Integer roleId) throws Exception;
}
