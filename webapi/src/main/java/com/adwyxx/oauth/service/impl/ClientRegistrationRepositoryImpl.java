package com.adwyxx.oauth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Auther: Leo.W
 * @Date: 2018/12/5 17:09
 */
@Service
public class ClientRegistrationRepositoryImpl implements ClientRegistrationRepository {
    @Autowired
    AuthClientDetailsService service;

    @Override
    public ClientRegistration findByRegistrationId(String s) {
        ClientDetails client = service.loadClientByClientId(s);
        ClientRegistration registration= ClientRegistration.withRegistrationId(client.getClientId())
                .clientId(client.getClientId())
                .clientSecret(client.getClientSecret())
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .scope(client.getScope())
                .tokenUri("/oauth/token")
                .build();

        return registration;
    }
}
