package com.adwyxx.oauth.service.impl;

import com.adwyxx.oauth.model.OAuth2Client;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Auther: Leo.W
 * @Date: 2018/12/11 15:03
 */
@Service
public class OAuth2ClientService implements OAuth2AuthorizedClientService {
    @Override
    public OAuth2Client loadAuthorizedClient(String s, String s1) {
        return null;
    }

    @Override
    public void saveAuthorizedClient(OAuth2AuthorizedClient oAuth2AuthorizedClient, Authentication authentication) {

    }

    @Override
    public void removeAuthorizedClient(String s, String s1) {

    }
}
