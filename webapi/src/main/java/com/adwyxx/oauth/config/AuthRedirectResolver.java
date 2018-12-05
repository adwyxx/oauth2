package com.adwyxx.oauth.config;

import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.endpoint.DefaultRedirectResolver;

import java.util.Set;

/**
 * @Description: 重写授权码模式认证client details跳转地址验证
 * @Auther: Leo.W
 * @Date: 2018/12/5 15:54
 */
public class AuthRedirectResolver extends DefaultRedirectResolver {

    @Override
    public String resolveRedirect(String requestedRedirect, ClientDetails client) throws OAuth2Exception {
        Set<String> authorizedGrantTypes = client.getAuthorizedGrantTypes();
        if (authorizedGrantTypes.isEmpty()) {
            throw new InvalidGrantException("A client must have at least one authorized grant type.");
        }
        //直接返回授权码请求的跳转地址，不再与client_details中的跳转地址列表验证
        return requestedRedirect;
    }
}
