package com.adwyxx.oauth.config;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description:
 * @Auther: Leo.W
 * @Date: 2018/12/6 14:43
 */
public class DefautlCorsFilter extends OncePerRequestFilter {

    public DefautlCorsFilter()
    {
        super();
    }

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");//* or origin as u prefer
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "PUT, POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With, Authorization");
        response.setHeader("XDomainRequestAllowed","1");
        //使前端能够获取到
        response.setHeader("Access-Control-Expose-Headers","download-status,download-filename,download-message");

        if (request.getMethod().equals("OPTIONS"))
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        else
            filterChain.doFilter(request, response);

    }

}
