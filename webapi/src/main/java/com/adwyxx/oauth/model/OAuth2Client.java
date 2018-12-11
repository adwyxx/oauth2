package com.adwyxx.oauth.model;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;

import java.io.Serializable;

/**
 * @Description:
 * @Auther: Leo.W
 * @Date: 2018/12/11 15:04
 */
public class OAuth2Client extends OAuth2AuthorizedClient implements Serializable {
    private static final long serialVersionUID = -6260812797375462208L;

    public OAuth2Client(ClientRegistration clientRegistration, String principalName, OAuth2AccessToken accessToken) {
        super(clientRegistration, principalName, accessToken);
    }

    public OAuth2Client(ClientRegistration clientRegistration, String principalName, OAuth2AccessToken accessToken, OAuth2RefreshToken refreshToken) {
        super(clientRegistration, principalName, accessToken, refreshToken);
    }
}
