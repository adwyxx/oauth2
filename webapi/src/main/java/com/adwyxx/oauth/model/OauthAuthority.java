package com.adwyxx.oauth.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * @Description:
 * @Auther: Leo.W
 * @Date: 2018/11/30 10:52
 */
public class OauthAuthority implements GrantedAuthority, Serializable {

    private static final long serialVersionUID = 3455166226544381675L;

    private String authority;

    @Override
    public String getAuthority() {
        return this.authority;
    }

    public void setAuthority(String authority) {
        this.authority=authority;
    }

    public OauthAuthority(String authority)
    {
        this.authority=authority;
    }
}
