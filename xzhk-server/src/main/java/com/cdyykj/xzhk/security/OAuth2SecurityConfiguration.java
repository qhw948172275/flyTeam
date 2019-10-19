package com.cdyykj.xzhk.security;


import com.cdyykj.xzhk.config.MyRedisTokenStore;
import com.cdyykj.xzhk.config.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class OAuth2SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private RedisConnectionFactory redisConnection;
    @Bean
    public MyUserDetailsService myUserDetailsService(){

        return new MyUserDetailsService();
    }
//    @Bean
//   public CorsConfigurationSource corsConfigurationSource() {
//        List<String> allowedOrigins = new ArrayList<String>();
//        allowedOrigins.add("*");
//
//
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(allowedOrigins);
//        configuration.addAllowedHeader("*");
//        configuration.addAllowedMethod("*");
//        configuration.setAllowCredentials(true);
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }
    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
        auth.userDetailsService(myUserDetailsService());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .anonymous().disable()
                .authorizeRequests()
                .antMatchers("/oauth/token","/swagger-ui.html#/**").permitAll()
                .and()
                .cors()//新加入
                .and()
                .csrf().disable();

    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Bean
    public TokenStore tokenStore() {
        return new MyRedisTokenStore(redisConnection);
    }

    @Bean
    @Autowired
    public TokenStoreUserApprovalHandler userApprovalHandler(TokenStore tokenStore){
        TokenStoreUserApprovalHandler handler = new TokenStoreUserApprovalHandler();
        handler.setTokenStore(tokenStore);
        handler.setRequestFactory(new DefaultOAuth2RequestFactory(clientDetailsService));
        handler.setClientDetailsService(clientDetailsService);
        return handler;
    }

    @Bean
    @Autowired
    public ApprovalStore approvalStore(TokenStore tokenStore) throws Exception {
        TokenApprovalStore store = new TokenApprovalStore();
        store.setTokenStore(tokenStore);
        return store;
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setHideUserNotFoundExceptions(false);
        provider.setUserDetailsService(myUserDetailsService());
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        return provider;
    }
}
