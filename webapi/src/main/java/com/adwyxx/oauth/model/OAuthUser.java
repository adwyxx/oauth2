package com.adwyxx.oauth.model;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: OAuth2认证用户信息
 * @Auther: Leo.W
 * @Date: 2018/12/5 16:33
 */
public class OAuthUser extends User implements OAuth2User, Serializable {

    private static final long serialVersionUID = 4298606610716972205L;

    //实现获取用户权限接口
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<OauthAuthority> authorites = new ArrayList<OauthAuthority>();
        authorites.add(new OauthAuthority("USER"));
        return  authorites;
    }

    //实现获取用户属性信息接口
    @Override
    public Map<String, Object> getAttributes() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id",getId());
        map.put("username",getUserName());
        map.put("name",getName());
        return map;
    }

    //实现获取用户名接口
    @Override
    public String getName() {
        return super.getUserName();
    }
}
