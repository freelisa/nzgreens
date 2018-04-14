package com.nzgreens.common.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.nzgreens.common.common.result.BaseResponse;
import com.nzgreens.common.entity.SysRole;
import com.nzgreens.common.entity.SysUser;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author helizheng
 * @since 2017-07-15
 */
public interface SysRoleService extends IService<SysRole> {
    /**
     * 查询所有角色 - 分页
     * @param page
     * @param sysRole
     * @return
     */
    BaseResponse<Page<SysRole>> queryRoleForPage(Page page, SysRole sysRole) throws Exception;

    /**
     * 删除角色
     * @param id
     * @return
     */
    BaseResponse<Boolean> deleteRole(Integer id) throws Exception;

    /**
     * 修改角色
     * @param sysRole
     * @return
     */
    BaseResponse<Boolean> updateRole(SysRole sysRole) throws Exception;

    /**
     * 新增角色
     * @param sysRole
     * @return
     */
    BaseResponse<Long> saveRole(SysRole sysRole) throws Exception;

    /**
     * 修改角色拥有的菜单资源
     * @param ids 资源ID字符串  比如1,2,3,4
     * @param roleId 角色ID
     * @return
     */
    BaseResponse saveRoleResource(String ids, Integer roleId) throws Exception;

    /**
     * 未被选择的角色
     * @param sysUser
     * @return
     */
    List<SysRole> queryNotSelectRoleById(SysUser sysUser) throws Exception;

    /**
     * 根据ID查询角色信息
     * @param id
     * @return
     */
    SysRole queryRoleById(Integer id) throws Exception;
}
