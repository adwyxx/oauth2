package com.adwyxx.oauth.service.impl;

import com.adwyxx.oauth.mapper.AccessTokenMapper;
import com.adwyxx.oauth.model.AccessToken;
import com.adwyxx.oauth.model.AuthorizationCode;
import com.adwyxx.oauth.service.AccessTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: AccessTokenService实现类
 * @Auther: Leo.W
 * @Date: 2018/11/29 09:59
 */
@Service
public class AccessTokenServiceImpl implements AccessTokenService {

    @Autowired
    private AccessTokenMapper accessTokenMapper;

    @Override
    public AccessToken generateAccessToken(String userName, String password, String clientId) {
        return null;
    }

    @Override
    public AccessToken generateAccessToken(AuthorizationCode authCode) {
        return null;
    }

    @Override
    public AccessToken refreshToken(String token) {
        AccessToken oldToken = accessTokenMapper.selectByToken(token);

        return null;
    }

    @Override
    public void removeToken(String token) {
        accessTokenMapper.deleteByToken(token);
    }
}
