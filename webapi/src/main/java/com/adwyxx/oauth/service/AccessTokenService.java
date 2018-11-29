package com.adwyxx.oauth.service;

import com.adwyxx.oauth.model.AccessToken;
import com.adwyxx.oauth.model.AuthorizationCode;

/**
 * @Description: Access Token Service
 * @Auther: Leo.W
 * @Date: 2018/11/29 09:59
 */
public interface AccessTokenService {

    public AccessToken generateAccessToken(String userName,String password,String clientId);

    public AccessToken generateAccessToken(AuthorizationCode authCode);

    public AccessToken refreshToken(String token);

    public void removeToken(String token);
}
