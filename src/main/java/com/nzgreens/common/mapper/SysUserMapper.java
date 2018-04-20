package com.nzgreens.common.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.nzgreens.common.common.utils.TreeObject;
import com.nzgreens.common.entity.SysResource;
import com.nzgreens.common.entity.SysRole;
import com.nzgreens.common.entity.SysUser;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author helizheng
 * @since 2017-07-15
 */
public interface SysUserMapper extends BaseMapper<SysUser> {
    /**
     * 查询用户列表 - 分页
     * @param page
     * @param sysUser
     * @return
     */
    List<SysUser> queryUserForPage(Pagination page, SysUser sysUser);

    /**
     * 根据用户名称查询该用户拥有的角色
     *
     * @param userName
     * @return
     */
    List<SysRole> queryUserRoleByName(String userName);

    /**
     * 根据用户ID查询该用户拥有的角色
     *
     * @param id
     * @return
     */
    List<SysRole> queryUserRoleById(Integer id);

    /**
     * 根据用户名称查询该用户拥有的菜单资源
     *
     * @param userName
     * @return
     */
    List<SysResource> queryUserResourceByName(String userName);

    /**
     * 未被选择的角色
     *
     * @param sysUser
     * @return
     */
    List<SysRole> queryNotSelectRoleById(SysUser sysUser);

    /**
     * 查询资源列表
     *
     * @param sysResource
     * @return
     */
    List<TreeObject> queryResource(SysResource sysResource);

    /**
     * 根据用户ID查询该用户权限菜单
     *
     * @param userId
     * @return
     */
    List<TreeObject> queryUserResourceById(Integer userId);

    /**
     * 根据角色ID查询权限菜单
     *
     * @param roleId
     * @return
     */
    List<TreeObject> queryRoleResourceById(Integer roleId);
}