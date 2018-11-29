package com.adwyxx.oauth.model;

import java.io.Serializable;
import java.util.Date;

public class AccessToken implements Serializable {

    private static final long serialVersionUID = -2969774942860603344L;
    private Long id;

    private String token;

    private String responseType;

    private String refreshToken;

    private Long exprises;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
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