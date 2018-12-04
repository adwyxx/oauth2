package com.adwyxx.oauth.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description:
 * @Auther: Leo.W
 * @Date: 2018/12/4 15:49
 */
public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        //验证成功后跳转设置
        getRedirectStrategy().sendRedirect(request, response, "http://127.0.0.1:9528/#/login?state=" + request.getParameter("state"));
    }
}
