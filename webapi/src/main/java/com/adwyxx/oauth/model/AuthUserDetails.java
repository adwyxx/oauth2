package com.adwyxx.oauth.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @Description:
 * @Auther: Leo.W
 * @Date: 2018/11/30 10:07
 */
public final class AuthUserDetails extends User implements UserDetails {
    private static final long serialVersionUID = 5563440845470929098L;

    //重写获取用户权限方法
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<OauthAuthority>  authorites = new ArrayList<OauthAuthority>();
        authorites.add(new OauthAuthority("USER"));
        return  authorites;
    }

    //重写获取用户密码方法
    @Override
    public String getPassword() {
        return super.getPassword();
    }

    //重写返回用户名方法
    @Override
    public String getUsername() {
        return super.getUserName();
    }

    //验证用户账号是否未过期
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //验证用户账号是否未被锁定
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //验证用户证书是否未过期
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //用户是否可用
    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
    * @description : 构造函数
    * @param user : 用户表数据库映射实体
    * @author : Leo.W
    * @date : 2018/11/30 10:20
    * @return : 认证用户信息
    **/
    public AuthUserDetails(User user)
    {
        super(user);
    }
}
