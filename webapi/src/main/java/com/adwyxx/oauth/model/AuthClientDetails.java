package com.adwyxx.oauth.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import java.util.*;

/**
 * @Description: OAuth2认证客户端信息
 * @Auther: Leo.W
 * @Date: 2018/11/30 14:28
 */
public class AuthClientDetails implements ClientDetails {

    private String clientId;
    private String clientSecret;
    private Set<String> authorizedGrantTypes;

    public AuthClientDetails (String clientId,String clientSecret)
    {
        this.clientId=clientId;
        this.clientSecret= clientSecret;
        //设置客户端生成token的类型
        HashSet<String> types=new HashSet<String>();
        types.add("password"); //用户密码类型
        types.add("authorization_code");//授权码类型
        types.add("refresh_token"); //刷新token
        types.add("client_credentials");//客户端凭据（客户端ID以及Key）类型
        this.authorizedGrantTypes = types;
    }

    public AuthClientDetails setClientId(String clientId)
    {
        this.clientId=clientId;
        return  this;
    }

    public AuthClientDetails setClientSecret(String clientSecret)
    {
        this.clientSecret=clientSecret;
        return  this;
    }

    public AuthClientDetails setAuthorizedGrantTypes(Set<String> types)
    {
        this.authorizedGrantTypes=types;
        return this;
    }

    //获取客户端id
    @Override
    public String getClientId() {
        return this.clientId;
    }

    //获取需要授权认证的资源
    @Override
    public Set<String> getResourceIds() {
        return null;
    }

    //是否需要Secret
    @Override
    public boolean isSecretRequired() {
        return false;
    }

    //获取客户端密码
    @Override
    public String getClientSecret() {
        return this.clientSecret;
    }

    @Override
    public boolean isScoped() {
        return false;
    }

    //获取授权范围
    @Override
    public Set<String> getScope() {
        HashSet<String> scopes = new HashSet<String>();
        scopes.add("read");
        scopes.add("write");
        return scopes;
    }

    //获取认证模式
    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return this.authorizedGrantTypes;
    }

    //获取认证后跳转的rul地址,必须有地址，否则/oauth/authorize请求authorization code时报错
    @Override
    public Set<String> getRegisteredRedirectUri() {
        HashSet<String> uri = new HashSet<String>();
        uri.add("http://www.baidu.com");
        return uri;
    }

    //返回客户端权限列表，不能为null
    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new OauthAuthority("oauth2"));
        return authorities;
    }

    //获取token的有效期（秒）
    @Override
    public Integer getAccessTokenValiditySeconds() {
        return 60*60;
    }

    //获取刷新token的有效期（秒）
    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return 60*60;
    }

    @Override
    public boolean isAutoApprove(String s) {
        return false;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return null;
    }
}
