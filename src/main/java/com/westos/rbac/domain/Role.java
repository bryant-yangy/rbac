package com.westos.rbac.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * 角色类
 * @author yihang
 */
@SuppressWarnings("serial")
public class Role implements Serializable {
    /**
     * 角色编号
     */
    private int id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 模块集合
     */
    private List<Module> modules;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return id == role.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
