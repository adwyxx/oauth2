package com.adwyxx.oauth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@SpringBootApplication
@MapperScan(basePackages = "com.adwyxx.oauth.mapper")
@EnableAuthorizationServer //创建Oauth2的认证授权服务器——provider，并开启认证授权服务器的相关默认配置
public class OAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(OAuthApplication.class, args);
    }
}
