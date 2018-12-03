package com.adwyxx.oauth.service.impl;

import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.stereotype.Service;

/**
 * @Description: AuthorizationCodeServices 实现类
 * @Auther: Leo.W
 * @Date: 2018/11/30 15:45
 */
@Service
public class AuthorizationCodeServicesImpl implements AuthorizationCodeServices {

    //重写AuthorizationCode生成方法
    @Override
    public String createAuthorizationCode(OAuth2Authentication oAuth2Authentication) {
        return null;
    }

    //重写AuthorizationCode销毁方法
    @Override
    public OAuth2Authentication consumeAuthorizationCode(String s) throws InvalidGrantException {
        return null;
    }
}
