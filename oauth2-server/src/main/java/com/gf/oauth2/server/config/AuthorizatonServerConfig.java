package com.gf.oauth2.server.config;

import com.gf.oauth2.server.customImpl.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.ArrayList;
import java.util.List;


@Configuration
@EnableAuthorizationServer
public class AuthorizatonServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private TokenEnhancer jwtTokenEnhancer;


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        String finalSecret="{bcrypt}"+new BCryptPasswordEncoder().encode("123456");

        //这里配置的grantType指该client端只能通过配置的grantType获取token
        clients.inMemory().withClient("client_1")
                .authorizedGrantTypes("password","refresh_token")
                .scopes("all")
                .secret(finalSecret);

    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore)
                .userDetailsService(userDetailsService)
//                .accessTokenConverter(jwtAccessTokenConverter)
                .authenticationManager(authenticationManager);
//        配置jwttoken转换器和增强器
        if (jwtAccessTokenConverter!= null && jwtTokenEnhancer!= null){
            TokenEnhancerChain chain=new TokenEnhancerChain();
            List<TokenEnhancer> enhancers=new ArrayList<>();
            enhancers.add(jwtTokenEnhancer);
            enhancers.add(jwtAccessTokenConverter);

            chain.setTokenEnhancers(enhancers);

            endpoints
                    .tokenEnhancer(chain)
                    .accessTokenConverter(jwtAccessTokenConverter);
        }

    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients();
    }
}
