package com.adwyxx.oauth.config;

import com.adwyxx.oauth.service.impl.AuthUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Description: WebSecurityConfigurer
 * @Auther: Leo.W
 * @Date: 2018/11/30 17:28
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    //配置UserDetailsService实例
    @Autowired
    private AuthUserDetailsService userDetailsService;

    // 配置AuthenticationManager实现类，解决AuthenticationManager不能自动注解问题
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    // 配置全局UserDetailsService服务器及用户密码加密方式
    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //不允许跨域
        http.csrf().disable();
        //设置拦截的oauth资源
        http.requestMatchers()
            .antMatchers("/oauth/**","/login/**")
            .and()
            .authorizeRequests()
            .antMatchers("/oauth/**","/login/**").authenticated();
    }

    // 配置密码加密方式
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
