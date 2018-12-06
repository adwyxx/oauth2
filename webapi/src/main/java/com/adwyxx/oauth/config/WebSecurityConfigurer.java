package com.adwyxx.oauth.config;

import com.adwyxx.oauth.handler.AuthenticationSuccessHandler;
import com.adwyxx.oauth.service.impl.AuthUserDetailsService;
import com.adwyxx.oauth.service.impl.OAuth2UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //设置拦截的oauth资源
        http.authorizeRequests()
                .antMatchers("/oauth/**","/login/**").authenticated() //设置需要权限认证的路径
                .antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
                .anyRequest().hasRole("USER") //设置oauth/authorize路径访问权限：任何含有USER角色的用户
            //.and()
            //.formLogin() //form表单登陆方式页面设置
            //    .permitAll() //form表单登陆页面受保护
            .and().oauth2Login()//oauth2登陆方式配置项
                .successHandler(new AuthenticationSuccessHandler()) //支持前后端分离，验证成功后跳转配置
                .loginPage("http://localhost:9090/#/login") //oauth2登陆页面
                .userInfoEndpoint().userService(new OAuth2UserServiceImpl());

        http.addFilterBefore(defautlCorsFilter(), UsernamePasswordAuthenticationFilter.class);

        //允许跨域
        http.cors()
            .and()
            .csrf().disable();
}

    // 配置密码加密方式
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
