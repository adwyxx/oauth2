package com.adwyxx.oauth.config;

import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description:
 * @Auther: Leo.W
 * @Date: 2018/12/10 16:56
 */
@Service
public class OAuth2AuthorizeRequestResolver implements OAuth2AuthorizationRequestResolver {
    @Override
    public OAuth2AuthorizationRequest resolve(HttpServletRequest httpServletRequest) {
        return null;
    }

    @Override
    public OAuth2AuthorizationRequest resolve(HttpServletRequest httpServletRequest, String s) {
        return null;
    }
}
