package com.westos.rbac.dao;

import com.westos.rbac.domain.Module;

import java.util.List;

/**
 * @author yihang
 */
public interface ModuleDao {

    /**
     * 查询所有模块
     * @return 模块集合
     */
    List<Module> findAll();

    /**
     * 查询某一角色的模块
     * @param roleId 角色编号
     * @return 模块集合
     */
    List<Module> findByRoleId(int roleId);
}
