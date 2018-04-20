package com.nzgreens.common.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nzgreens.common.common.result.BaseResponse;
import com.nzgreens.common.entity.SysResource;
import com.nzgreens.common.entity.SysRole;
import com.nzgreens.common.entity.SysUser;
import com.nzgreens.common.entity.SysUserRole;
import com.nzgreens.common.mapper.SysUserMapper;
import com.nzgreens.common.mapper.SysUserRoleMapper;
import com.nzgreens.common.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author helizheng
 * @since 2017-07-15
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    /**
     * 查询用户列表 - 分页
     *
     * @param page
     * @param sysUser
     * @return
     */
    public BaseResponse<Page<SysUser>> queryUserForPage(Page page, SysUser sysUser) throws Exception{
        BaseResponse<Page<SysUser>> resd = new BaseResponse<>();
        if(StringUtils.isEmpty(sysUser.getAccountName())){
            sysUser.setAccountName(null);
        }
        List<SysUser> sysUsers = sysUserMapper.selectPage(page, new EntityWrapper<>(sysUser)
                .like("user_name", sysUser.getUserName()));
        page.setRecords(sysUsers);
        resd.setData(page);
        return resd;
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    public BaseResponse<Boolean> deleteUser(Integer id) throws Exception{
        //删除用户
        sysUserMapper.deleteById(id);
        //删除用户角色中间表数据
        sysUserRoleMapper.deleteById(id);
        return new BaseResponse<>();
    }

    /**
     * 修改用户
     *
     * @param sysUser
     * @return
     */
    public BaseResponse<Boolean> updateUser(SysUser sysUser) throws Exception{
        sysUserMapper.updateById(sysUser);
        return new BaseResponse<>();
    }

    /**
     * 新增用户
     *
     * @param sysUser
     * @return
     */
    public BaseResponse<Integer> saveUser(SysUser sysUser) throws Exception{
        BaseResponse<Integer> resd = new BaseResponse<>();
        Integer insert = sysUserMapper.insert(sysUser);
        resd.setData(insert);
        return resd;
    }

    /**
     * 新增用户拥有的角色
     * @param ids
     * @param sysUserRole
     * @return
     */
    public BaseResponse<Long> saveUserRole(String ids,SysUserRole sysUserRole) throws Exception{
        BaseResponse<Long> resd = new BaseResponse<>();
        if(StringUtils.isNotEmpty(ids)){
            //删除之前的关系
            sysUserRoleMapper.deleteById(sysUserRole.getUserId());
            //保存现在的关系
            String[] roleIds = ids.split(",");
            for (int i = 0; i < roleIds.length; i++) {
                sysUserRole.setRoleId(Integer.parseInt(roleIds[i]));
                sysUserRoleMapper.insert(sysUserRole);
            }
        }
        return resd;
    }

    /**
     * 根据用户ID查询用户信息
     *
     * @param id
     * @return
     */
    public SysUser queryUserById(Integer id) throws Exception{
        return sysUserMapper.selectById(id);
    }

    /**
     * 根据用户名称查询用户信息
     *
     * @param userName
     * @return
     */
    public SysUser queryUserByName(String userName){
        SysUser user = null;
        try {
            user = new SysUser();
            user.setAccountName(userName);
            user = sysUserMapper.selectOne(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * 根据用户名称查询该用户拥有的角色
     *
     * @param userName
     * @return
     */
    public Set<String> queryUserRoleByName(String userName){
        Set<String> set = null;
        try {
            List<SysRole> list = sysUserMapper.queryUserRoleByName(userName);
            if(list != null && list.size() > 0){
                set = new HashSet<>();
            }
            for(SysRole role : list){
                set.add(role.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return set;
    }

    /**
     * 根据用户ID查询该用户拥有的角色
     *
     * @param id
     * @return
     */
    public List<SysRole> queryUserRoleById(Integer id) throws Exception{
        List<SysRole> list = sysUserMapper.queryUserRoleById(id);
        return list;
    }

    /**
     * 根据用户名称查询该用户拥有的菜单资源
     *
     * @param userName
     * @return
     */
    public Set<String> queryUserResourceByName(String userName) {
        Set<String> set = null;
        try {
            List<SysResource> list = sysUserMapper.queryUserResourceByName(userName);
            if(list != null && list.size() > 0){
                set = new HashSet<>();
            }
            for(SysResource resource : list){
                set.add(resource.getPermission());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return set;
    }

}
