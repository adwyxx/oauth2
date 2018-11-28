package com.adwyxx.oauth.model;

import java.util.Date;

public class AccessToken {
    private Long id;

    private Long exprises;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getExprises() {
        return exprises;
    }

    public void setExprises(Long exprises) {
        this.exprises = exprises;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}