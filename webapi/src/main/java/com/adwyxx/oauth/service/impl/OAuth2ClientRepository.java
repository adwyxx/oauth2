package com.adwyxx.oauth.service.impl;

import com.adwyxx.oauth.model.OAuth2Client;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description:
 * @Auther: Leo.W
 * @Date: 2018/12/11 15:10
 */
@Service
public class OAuth2ClientRepository implements OAuth2AuthorizedClientRepository {
    @Override
    public OAuth2Client loadAuthorizedClient(String s, Authentication authentication, HttpServletRequest httpServletRequest) {
        return null;
    }

    @Override
    public void saveAuthorizedClient(OAuth2AuthorizedClient oAuth2AuthorizedClient, Authentication authentication, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

    }

    @Override
    public void removeAuthorizedClient(String s, Authentication authentication, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

    }
}
