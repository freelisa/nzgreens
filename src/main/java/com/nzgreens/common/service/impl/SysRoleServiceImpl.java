package com.nzgreens.common.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nzgreens.common.common.result.BaseResponse;
import com.nzgreens.common.entity.SysRole;
import com.nzgreens.common.entity.SysRoleResource;
import com.nzgreens.common.entity.SysUser;
import com.nzgreens.common.mapper.SysRoleMapper;
import com.nzgreens.common.mapper.SysRoleResourceMapper;
import com.nzgreens.common.mapper.SysUserMapper;
import com.nzgreens.common.service.SysRoleService;
import com.nzgreens.common.shiro.MyRealm;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
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
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysRoleResourceMapper sysRoleResourceMapper;
    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 查询所有角色 - 分页
     *
     * @param page
     * @param sysRole
     * @return
     */
    public BaseResponse<Page<SysRole>> queryRoleForPage(Page page, SysRole sysRole) throws Exception{
        BaseResponse<Page<SysRole>> resd = new BaseResponse<>();
        List<SysRole> sysRoles = sysRoleMapper.selectPage(page, null);
        page.setRecords(sysRoles);
        resd.setData(page);
        return resd;
    }

    /**
     * 删除角色
     *
     * @param id
     * @return
     */
    public BaseResponse<Boolean> deleteRole(Integer id) throws Exception{
        sysRoleMapper.deleteById(id);
        sysRoleResourceMapper.deleteById(id);
        return new BaseResponse<>();
    }

    /**
     * 修改角色
     *
     * @param sysRole
     * @return
     */
    public BaseResponse<Boolean> updateRole(SysRole sysRole) throws Exception{
        sysRoleMapper.updateById(sysRole);
        return new BaseResponse<>();
    }

    /**
     * 新增角色
     *
     * @param sysRole
     * @return
     */
    public BaseResponse<Long> saveRole(SysRole sysRole) throws Exception{
        BaseResponse<Long> resd = new BaseResponse<>();
        sysRoleMapper.insert(sysRole);
        return resd;
    }

    /**
     * 修改角色拥有的菜单资源
     *
     * @param ids    资源ID字符串  比如1,2,3,4
     * @param roleId 角色ID
     * @return
     */
    public BaseResponse saveRoleResource(String ids, Integer roleId) throws Exception{
        //首先清除缓存
        RealmSecurityManager securityManager = (RealmSecurityManager) SecurityUtils.getSecurityManager();
        MyRealm userRealm = (MyRealm)securityManager.getRealms().iterator().next();
        userRealm.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
        //业务操作
        SysRoleResource roleResource = new SysRoleResource();
        roleResource.setRoleId(roleId);
        sysRoleResourceMapper.deleteById(roleId);
        if(StringUtils.isNotEmpty(ids)){
            String[] id = ids.split(",");
            for (int i = 0; i < id.length; i++) {
                Integer resId = Integer.parseInt(id[i]);
                roleResource.setResourceId(resId);
                sysRoleResourceMapper.insert(roleResource);
            }
        }
        return new BaseResponse();
    }

    /**
     * 未被选择的角色
     *
     * @param sysUser
     * @return
     */
    public List<SysRole> queryNotSelectRoleById(SysUser sysUser) throws Exception{
        return sysUserMapper.queryNotSelectRoleById(sysUser);
    }

    /**
     * 根据ID查询角色信息
     *
     * @param id
     * @return
     */
    public SysRole queryRoleById(Integer id) throws Exception{
        return sysRoleMapper.selectById(id);
    }
}
