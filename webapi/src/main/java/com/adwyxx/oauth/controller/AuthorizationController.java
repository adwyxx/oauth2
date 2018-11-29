package com.adwyxx.oauth.controller;

import com.adwyxx.oauth.model.AccessToken;
import com.adwyxx.oauth.service.AccessTokenService;
import com.adwyxx.oauth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: Authorization相关Api接口
 * @Auther: Leo.W
 * @Date: 2018/11/29 09:51
 */
@RestController
@RequestMapping("/auth")
public class AuthorizationController {
    @Autowired
    private UserService userService;
    @Autowired
    private AccessTokenService accessTokenService;

    @RequestMapping("/authorize")
    public AccessToken generateToken(String code)
    {
        return  null;
    }

    @RequestMapping("/authorizecode")
    public AccessToken getAuthorizeCode(String token)
    {
        return  null;
    }
}
