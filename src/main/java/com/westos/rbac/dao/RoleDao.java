package com.westos.rbac.dao;

import com.westos.rbac.domain.Role;

import java.util.List;

/**
 * @author yihang
 */
public interface RoleDao {

    /**
     * 查询所有角色
     *
     * @return 角色集合
     */
    List<Role> findAll();

    /**
     * 查询某一用户的角色
     * @param userId 用户编号
     * @return 角色集合
     */
    List<Role> findByUserId(int userId);
}
