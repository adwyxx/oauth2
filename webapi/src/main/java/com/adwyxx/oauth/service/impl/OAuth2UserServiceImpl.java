package com.adwyxx.oauth.service.impl;

import com.adwyxx.oauth.model.OAuthUser;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;

/**
 * @Description:
 * @Auther: Leo.W
 * @Date: 2018/12/5 16:49
 */
public class OAuth2UserServiceImpl implements OAuth2UserService<OAuth2UserRequest,OAuth2User> {
    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        String uri = oAuth2UserRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUri();
        String tokenValue = oAuth2UserRequest.getAccessToken().getTokenValue();
        uri = uri + "?access_token=" + tokenValue;

        OAuthUser user = new OAuthUser();
        user.setUserName("admin");
        user.setId(1);
        user.setDisplayName("admin");
        return user;
    }
}
