package com.adwyxx.oauth.model;

import java.io.Serializable;
import java.util.Date;

public class OAuthClient implements Serializable {
    private static final long serialVersionUID = -8951188975058596418L;

    private String clientId;

    private String clientName;

    private String clientSecret;

    private Date createTime;

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId == null ? null : clientId.trim();
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName == null ? null : clientName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}