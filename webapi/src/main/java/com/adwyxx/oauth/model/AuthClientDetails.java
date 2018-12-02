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
        HashSet<String> types=new HashSet<String>();
        types.add("password");
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

    @Override
    public String getClientId() {
        return this.clientId;
    }

    @Override
    public Set<String> getResourceIds() {
        return null;
    }

    @Override
    public boolean isSecretRequired() {
        return false;
    }

    @Override
    public String getClientSecret() {
        return this.clientSecret;
    }

    @Override
    public boolean isScoped() {
        return false;
    }

    @Override
    public Set<String> getScope() {
        return null;
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return this.authorizedGrantTypes;
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return null;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return 60*60;
    }

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
