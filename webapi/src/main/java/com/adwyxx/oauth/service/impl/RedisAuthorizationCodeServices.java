package com.adwyxx.oauth.service.impl;

import com.adwyxx.oauth.utils.RedisHelper;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.code.RandomValueAuthorizationCodeServices;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Auther: Leo.W
 * @Date: 2018/12/7 15:39
 */
@Service
public class RedisAuthorizationCodeServices extends RandomValueAuthorizationCodeServices {
    @Override
    protected void store(String code, OAuth2Authentication authentication) {
        RedisHelper.set(code,authentication);
    }

    @Override
    protected OAuth2Authentication remove(String code) {
        Object obj = RedisHelper.get(code);
        if(null != obj)
        {
            return (OAuth2Authentication) obj;
        }
        return null;
    }
}
