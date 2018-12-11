package com.adwyxx.oauth.config;

import com.adwyxx.oauth.handler.AuthenticationSuccessHandler;
import com.adwyxx.oauth.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


/**
 * @Description: WebSecurityConfigurer
 * @Auther: Leo.W
 * @Date: 2018/11/30 17:28
 */
@Order(2)
@Configuration
@EnableWebSecurity
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Value(value = "${spring.security.oauth2.loginPage}")
    private String loginPage;

    //配置UserDetailsService实例
    @Autowired
    private AuthUserDetailsService userDetailsService;

    @Autowired
    private OAuth2AuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    private OAuth2AuthorizeRequestResolver authorizeRequestResolver;
    @Autowired
    private OAuth2ClientRepository oAuth2ClientRepository;
    @Autowired
    private OAuth2ClientService oAuth2ClientService;

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

//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.addAllowedOrigin("*");
//        configuration.addAllowedHeader("*");
//        configuration.addAllowedMethod("*");
//        configuration.setAllowCredentials(true);
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }

    //解决前后端分离跨域问题
    @Bean
    public DefautlCorsFilter defautlCorsFilter() throws Exception {
        return new DefautlCorsFilter();
    }

    // 配置密码加密方式
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // authorization code跳转至登陆页面前设置
    @Bean
    public OAuth2AuthenticationEntryPoint oAuth2AuthenticationEntryPoint() {
        return new OAuth2AuthenticationEntryPoint(this.loginPage);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //允许跨域
        http.cors()
                .and()
                .csrf().disable();
        http.addFilterBefore(defautlCorsFilter(), UsernamePasswordAuthenticationFilter.class);

        //设置拦截的oauth资源
        http.authorizeRequests()
                .antMatchers("/oauth/**","/login/**").authenticated() //设置需要权限认证的路径
                .antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
                .anyRequest().hasRole("USER") //设置oauth/authorize路径访问权限：任何含有USER角色的用户
            //.and()
            //.formLogin() //form表单登陆方式页面设置
            //    .permitAll() //form表单登陆页面受保护
            .and().exceptionHandling()
                .authenticationEntryPoint(oAuth2AuthenticationEntryPoint()) //authorization code获取跳转至登陆页面设置
            .and()
            .oauth2Client()
                .authorizedClientRepository(oAuth2ClientRepository)
                .authorizedClientService(oAuth2ClientService)
                .authorizationCodeGrant()
                .and()
                //.clientRegistrationRepository()
            .and().oauth2Login()//oauth2登陆方式配置项
                .authorizationEndpoint()
                //.authorizationRequestResolver() //处理oauth2 authorization code请求的代理类设置
                //.authorizationRequestRepository()
                //.baseUri("") //获取Authorization code的api地址，默认为/oauth2/authorization
                //.authorizationRequestResolver(authorizeRequestResolver)
                .and()
                .successHandler(new AuthenticationSuccessHandler()) //支持前后端分离，验证成功后跳转配置
                .loginPage(this.loginPage) //oauth2登陆页面
                .userInfoEndpoint().userService(new OAuth2UserServiceImpl());
    }

}
