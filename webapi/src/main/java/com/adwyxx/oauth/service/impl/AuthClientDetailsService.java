package com.adwyxx.oauth.service.impl;

import com.adwyxx.oauth.mapper.OAuthClientMapper;
import com.adwyxx.oauth.model.OAuthClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Auther: Leo.W
 * @Date: 2018/11/30 14:20
 */
@Service
public class AuthClientDetailsService implements ClientDetailsService {

    @Autowired
    OAuthClientMapper mapper;

    @Override
    public ClientDetails loadClientByClientId(String s) throws ClientRegistrationException {
        OAuthClient client = mapper.selectByPrimaryKey(s);
        return null;
    }
}
