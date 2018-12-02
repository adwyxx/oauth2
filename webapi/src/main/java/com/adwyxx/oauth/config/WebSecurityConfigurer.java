package com.adwyxx.oauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @Description:
 * @Auther: Leo.W
 * @Date: 2018/11/30 17:28
 */
@Configuration
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    // 解决AuthenticationManager不能自动注解问题
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
