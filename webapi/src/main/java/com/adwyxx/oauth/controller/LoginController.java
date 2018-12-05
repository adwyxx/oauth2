package com.adwyxx.oauth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ResolvableType;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestRedirectFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: Leo.W
 * @Date: 2018/12/5 17:03
 */
@RestController
@RequestMapping("/lgoin")
public class LoginController {

    @Autowired
    private ClientRegistrationRepository clientRegistrationRepository;

    @GetMapping("oauthLogin")
    public Object oauthLogin() {
        Map result = new HashMap();
        result.put("code", 201);
        result.put("msg", "Log in failure");
        return result;
    }

    @GetMapping("oauth2Client")
    public Object oauth2Client(HttpServletRequest request) {
        Iterable<ClientRegistration> clientRegistrations = null;
        ResolvableType type = ResolvableType.forInstance(clientRegistrationRepository).as(Iterable.class);
        if (type != ResolvableType.NONE && ClientRegistration.class.isAssignableFrom(type.resolveGenerics()[0])) {
            clientRegistrations = (Iterable<ClientRegistration>) clientRegistrationRepository;
        }
        StringBuffer url = request.getRequestURL();
        String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).toString();

        List data = new ArrayList();
        clientRegistrations.forEach(registration -> {
            Map client = new HashMap();
            client.put("clientName", registration.getClientName());
            client.put("clientUrl", tempContextUrl + OAuth2AuthorizationRequestRedirectFilter.DEFAULT_AUTHORIZATION_REQUEST_BASE_URI + "/" + registration.getRegistrationId());
            data.add(client);
        });

        Map result = new HashMap();
        result.put("code", 200);
        result.put("data", data);
        return result;
    }

}
