package com.adwyxx.oauth.config;

import com.adwyxx.oauth.service.impl.AuthClientDetailsService;
import com.adwyxx.oauth.service.impl.AuthUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;

/**
 * @Description: 认证服务配置
 * @Auther: Leo.W
 * @Date: 2018/11/29 18:02
 */
@Configuration
@EnableAuthorizationServer//开启配置 OAuth 2.0 认证授权服务
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private AuthUserDetailsService userDetailsService;

    @Autowired
    private AuthClientDetailsService clientDetailsService;

    /**
     * @description : 配置AccessToken的存储方式：RedisTokenStore存储。
     * Spring Cloud Security OAuth2通过DefaultTokenServices类来完成token生成、过期等 OAuth2 标准规定的业务逻辑，
     * 而DefaultTokenServices又是通过TokenStore接口完成对生成数据的持久化。
     * 在上面的demo中，TokenStore的默认实现为InMemoryTokenStore，即内存存储。
     * @param redisConnectionFactory : Redis工厂类
     * @author : Leo.W
     * @date : 2018/11/29 18:00
     * @return : Token存储方式
     **/
    @Bean
    public TokenStore tokenStore(RedisConnectionFactory redisConnectionFactory)
    {
        RedisTokenStore store = new RedisTokenStore(redisConnectionFactory);
        return store;
    }

    /**
     * 配置 oauth_client_details【client_id和client_secret等】信息的认证【检查ClientDetails的合法性】服务
     * 设置 认证信息的来源：数据库 (可选项：数据库和内存,使用内存一般用来作测试)
     * 自动注入：ClientDetailsService的实现类 JdbcClientDetailsService (检查 ClientDetails 对象)
     * 用来配置客户端详情服务（ClientDetailsService），客户端详情信息在这里进行初始化，你能够把客户端详情信息写死在这里或者是通过数据库来存储调取详情信息。
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //设置client details数据来源为jdbc数据库。
        clients.jdbc(dataSource);
        //设置自动定义clientDetailsService，如果不设置则系统默认查询数据库中的oauth_client_details表其字段必须与系统自定义的一致
        clients.withClientDetails(clientDetailsService);
    }


    /**
     * 配置授权（authorization）以及令牌（token）的访问端点和令牌服务(token services)。
     * 密码模式下配置认证管理器 AuthenticationManager,并且设置 AccessToken的存储介质tokenStore,如果不设置，则会默认使用内存当做存储介质。
     * 而该AuthenticationManager将会注入 2个Bean对象用以检查(认证)
     * 1、ClientDetailsService的实现类 JdbcClientDetailsService (检查 ClientDetails 对象)
     * 2、UserDetailsService的实现类 CustomUserDetailsService (检查 UserDetails 对象)
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception
    {
        // 设置客户端信息Service
        //endpoints.setClientDetailsService(clientDetailsService);
        // 设置
        endpoints.authenticationManager(authenticationManager)
                .tokenStore(tokenStore)
                //.accessTokenConverter(accessTokenConverter)
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST) //支持GET\POS请求获取token
                //.tokenServices(tokenService)
                //.authorizationCodeServices(authorizationCodeService) //不设置的话默认内存存储authorizationCode
                .userDetailsService(userDetailsService)
                .reuseRefreshTokens(true); //开启刷新token模式

        // 设置替代系统默认的请求路径，第一个参数为系统默认路径，第二个参数为替代后的路径
        //endpoints.pathMapping("/oauth/authorize","/authorize");

        // 当你设置了这个东西（即 TokenGranter 接口实现），那么授权将会交由你来完全掌控，
        // 并且会忽略掉上面的tokenStore,tokenService,userDetailsService,authorizationCodeService属性
        //endpoints.tokenGranter(tokenGranter);
    }

    /**
     *  配置：安全检查流程，配置令牌端点(Token Endpoint)的安全约束.
     *  默认过滤器：BasicAuthenticationFilter
     *  1、oauth_client_details表中clientSecret字段加密【ClientDetails属性secret】
     *  2、CheckEndpoint类的接口 oauth/check_token 无需经过过滤器过滤，默认值：denyAll()
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients();//允许客户表单认证,允许访问oauth/authorize?response_type=code获取授权码
        security.passwordEncoder(new BCryptPasswordEncoder());//设置oauth_client_details中的密码编码器
        security.tokenKeyAccess("permitAll()"); // 配置token获取和验证时的策略
        security.checkTokenAccess("permitAll()");//对于CheckEndpoint控制器[框架自带的校验]的/oauth/check端点允许所有客户端发送器请求而不会被Spring-security拦截
    }

}
