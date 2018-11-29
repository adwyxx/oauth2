package com.adwyxx.oauth.model;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 2447001179998212205L;

    private String user;

    private Long currentConnections;

    private Long totalConnections;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user == null ? null : user.trim();
    }

    public Long getCurrentConnections() {
        return currentConnections;
    }

    public void setCurrentConnections(Long currentConnections) {
        this.currentConnections = currentConnections;
    }

    public Long getTotalConnections() {
        return totalConnections;
    }

    public void setTotalConnections(Long totalConnections) {
        this.totalConnections = totalConnections;
    }
}