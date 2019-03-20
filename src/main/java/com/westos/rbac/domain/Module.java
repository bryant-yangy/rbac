package com.westos.rbac.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 模块(权限)类
 *
 * @author yihang
 */
@SuppressWarnings("serial")
public class Module implements Serializable {
    /**
     * 模块编号
     */
    private int id;

    /**
     * 模块名称
     */
    private String name;

    /**
     * 父模块编号
     */
    private int pid;

    /**
     * 权限代码
     */
    private String code;

    /**
     * 子模块集合
     */
    private List<Module> children = new ArrayList<>();

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

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Module> getChildren() {
        return children;
    }

    public void setChildren(List<Module> children) {
        this.children = children;
    }


}