package com.nzgreens.common.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.nzgreens.common.entity.Users;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.nzgreens.common.entity.extend.UserManageItemDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
  * 用户表 Mapper 接口
 * </p>
 *
 * @author sylar
 * @since 2018-04-05
 */
public interface UsersMapper extends BaseMapper<Users> {

    /**
     * 查询用户管理列表
     * @param page
     * @param agentUserId
     * @return
     */
    List<UserManageItemDTO> selectUserForPage(Page page,@Param("agentUserId") Long agentUserId);

}