package com.westos.rbac.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 组织机构类
 * @author yihang
 */
@SuppressWarnings("serial")
public class Org implements Serializable {
    /**
     * 编号
     */
    private int id;

    /**
     * 名称
     */
    private String name;

    /**
     * 父编号
     */
    private int pid;

    /**
     * 下级机构集合
     */
    private List<Org> children;

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

    public List<Org> getChildren() {
        return children;
    }

    public void setChildren(List<Org> children) {
        this.children = children;
    }
}
