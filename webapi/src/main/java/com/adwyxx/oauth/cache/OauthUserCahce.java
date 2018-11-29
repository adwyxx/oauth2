package com.adwyxx.oauth.cache;

import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @Description:
 * @Auther: Leo.W
 * @Date: 2018/11/29 17:01
 */
public class OauthUserCahce implements UserCache {


    @Override
    public UserDetails getUserFromCache(String s) {
        return null;
    }

    @Override
    public void putUserInCache(UserDetails userDetails) {

    }

    @Override
    public void removeUserFromCache(String s) {

    }
}
