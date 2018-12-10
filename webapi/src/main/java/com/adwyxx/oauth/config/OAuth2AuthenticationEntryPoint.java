package com.adwyxx.oauth.config;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.*;
import org.springframework.security.web.util.RedirectUrlBuilder;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.Assert;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description:
 * @Auther: Leo.W
 * @Date: 2018/12/10 13:59
 */
public class OAuth2AuthenticationEntryPoint implements AuthenticationEntryPoint {
    private String loginFormUrl;
    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    private PortResolver portResolver = new PortResolverImpl();

    public OAuth2AuthenticationEntryPoint(String loginFormUrl) {
        Assert.notNull(loginFormUrl, "loginFormUrl cannot be null");
        this.loginFormUrl = loginFormUrl;
    }

    @Override
    public  void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        String redirectUrl = buildRedirectUrlToLoginPage(request,response,authException);

        this.redirectStrategy.sendRedirect(request, response, redirectUrl);
    }

    protected String buildRedirectUrlToLoginPage(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) {
        if (UrlUtils.isAbsoluteUrl(this.loginFormUrl)) {
            StringBuilder url = new StringBuilder();
            url.append(this.loginFormUrl);
            url.append("?");
            url.append("redirect_uri=");
            url.append(request.getParameter("redirect_uri"));
            return url.toString();
        } else {
            int serverPort = this.portResolver.getServerPort(request);
            String scheme = request.getScheme();
            RedirectUrlBuilder urlBuilder = new RedirectUrlBuilder();
            urlBuilder.setScheme(scheme);
            urlBuilder.setServerName(request.getServerName());
            urlBuilder.setPort(serverPort);
            urlBuilder.setContextPath(request.getContextPath());
            urlBuilder.setPathInfo(this.loginFormUrl);
            if(request.getParameterMap().containsKey("redirect_uri"))
            {
                urlBuilder.setQuery("redirect_uri="+request.getParameter("redirect_uri"));
            }
            return urlBuilder.getUrl();
        }
    }
}
