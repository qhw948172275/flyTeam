package com.cdyykj.xzhk.security;


import com.cdyykj.system.commons.RedisUtils;
import com.cdyykj.xzhk.controller.api.commons.RedisClientDetailsService;
import com.cdyykj.xzhk.controller.api.commons.UaaConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
    private static String REALM="USER_REALM";

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    private UserApprovalHandler userApprovalHandler;

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private BootOAuth2AuthExceptionEntryPoint bootOAuth2AuthExceptionEntryPoint;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

//        clients.inMemory()
//                .withClient("user-client")//客户端ID
//                .authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
//                .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
//                .scopes("read", "write", "trust")//授权用户的操作权限
//                .secret(passwordEncoder().encode("android"))//5.0以上版本需要加密密码
//                .accessTokenValiditySeconds(60*60).//token有效期为1小时
//                refreshTokenValiditySeconds(600);//刷新token有效期为600秒
        clients.withClientDetails(clientDetails());
    }
    @Bean
    public ClientDetailsService clientDetails() {
        return new JdbcClientDetailsService(dataSource);
    }
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore).userApprovalHandler(userApprovalHandler)
                .authenticationManager(authenticationManager);
        endpoints.exceptionTranslator(bootOAuth2AuthExceptionEntryPoint);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.realm(REALM+"/client");
        oauthServer.allowFormAuthenticationForClients();
    }

    @Bean
    public RedisClientDetailsService getRedisClientDetailsService(){
        RedisClientDetailsService redisClientDetailsService=new RedisClientDetailsService(dataSource);
        redisClientDetailsService.setRedisTemplate(redisUtils.getRedisTemplate());
        return redisClientDetailsService;
    }
}

