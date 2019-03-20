package com.westos.rbac.domain;

/**
 * 订单类
 *
 * @author yihang
 */
public class Order {
    /**
     * 订单编号
     */
    private int id;

    /**
     * 客户编号
     */
    private int customerId;

    /**
     * 组织机构编号
     */
    private int orgId;

    /**
     * 总金额
     */
    private Double total;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

}
