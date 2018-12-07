package com.adwyxx.oauth.model;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import java.io.Serializable;

/**
 * @Description: 自定Access Token信息
 * @Auther: Leo.W
 * @Date: 2018/12/7 14:32
 */
public class AccessToken extends DefaultOAuth2AccessToken implements Serializable {

    private static final long serialVersionUID = -4469211381131458978L;

    private Integer userId;
    private String username;
    private String clientId;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    //获取username
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    //获取client id
    public String getClientId() {
        return clientId;
    }


    public void setClientId(String clientId) {
        this.clientId = clientId;
    }


    public AccessToken(String value)
    {
        super(value);
    }

    public AccessToken(OAuth2AccessToken token)
    {
       super(token);
    }
}
