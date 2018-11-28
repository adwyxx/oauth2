package com.adwyxx.oauth.model;

public class AccessTokenWithBLOBs extends AccessToken {
    private byte[] token;

    private byte[] refreshToken;

    public byte[] getToken() {
        return token;
    }

    public void setToken(byte[] token) {
        this.token = token;
    }

    public byte[] getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(byte[] refreshToken) {
        this.refreshToken = refreshToken;
    }
}