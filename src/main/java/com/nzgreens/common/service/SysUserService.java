package com.nzgreens.common.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.nzgreens.common.common.result.BaseResponse;
import com.nzgreens.common.entity.SysRole;
import com.nzgreens.common.entity.SysUser;
import com.nzgreens.common.entity.SysUserRole;

import java.util.List;
import java.util.Set;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author helizheng
 * @since 2017-07-15
 */
public interface SysUserService extends IService<SysUser> {
    /**
     * 查询用户列表 - 分页
     * @param page
     * @param sysUser
     * @return
     */
    BaseResponse<Page<SysUser>> queryUserForPage(Page page, SysUser sysUser) throws Exception;

    /**
     * 删除用户
     * @param id
     * @return
     */
    BaseResponse<Boolean> deleteUser(Integer id) throws Exception;

    /**
     * 修改用户
     * @param sysUser
     * @return
     */
    BaseResponse<Boolean> updateUser(SysUser sysUser) throws Exception;

    /**
     * 新增用户
     * @param sysUser
     * @return
     */
    BaseResponse<Integer> saveUser(SysUser sysUser) throws Exception;

    /**
     * 新增用户拥有的角色
     * @param ids
     * @param sysUserRole
     * @return
     */
    BaseResponse<Long> saveUserRole(String ids, SysUserRole sysUserRole) throws Exception;

    /**
     * 根据用户ID查询用户信息
     * @param id
     * @return
     */
    SysUser queryUserById(Integer id) throws Exception;

    /**
     * 根据用户名称查询用户信息
     * @param userName
     * @return
     */
    SysUser queryUserByName(String userName);

    /**
     * 根据用户名称查询该用户拥有的角色
     * @param userName
     * @return
     */
    Set<String> queryUserRoleByName(String userName);

    /**
     * 根据用户ID查询该用户拥有的角色
     * @param id
     * @return
     */
    List<SysRole> queryUserRoleById(Integer id) throws Exception;

    /**
     * 根据用户名称查询该用户拥有的菜单资源
     * @param userName
     * @return
     */
    Set<String> queryUserResourceByName(String userName);
}
