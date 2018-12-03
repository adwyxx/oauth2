package com.adwyxx.oauth.service.impl;

import com.adwyxx.oauth.mapper.OAuthClientMapper;
import com.adwyxx.oauth.model.AuthClientDetails;
import com.adwyxx.oauth.model.OAuthClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

/**
 * @Description: ClientDetailsService实现类
 * @Auther: Leo.W
 * @Date: 2018/11/30 14:20
 */
@Service
public class AuthClientDetailsService implements ClientDetailsService {

    @Autowired
    OAuthClientMapper mapper;

    //重写根据client id获取client details信息方法
    @Override
    public ClientDetails loadClientByClientId(String s) throws ClientRegistrationException {
        //从数据库读取client details
        OAuthClient client = mapper.selectByPrimaryKey(s);
        if(null==client)
        {
            throw new ClientRegistrationException("未授权的ClientID："+s);
        }
        AuthClientDetails details = new AuthClientDetails(client.getClientId(),client.getClientSecret());
        return details;
    }
}
