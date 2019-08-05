package com.gf.oauth2.server.config;

import com.gf.oauth2.server.jwt.JwtTokenEnhancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class TokenStoreConfig {

    @Autowired
    private MyRedisTokenStore myRedisTokenStore;

//    @Bean
//    @Primary
//    public TokenStore redisTokenStore(){
//        return myRedisTokenStore;
//    }

    @Configuration
    public static class JwtTokenConfig{

        private String signKey="123456789";

        @Bean
        @Primary
        public TokenStore jwtTokenStore(){
            return new JwtTokenStore(jwtAccessTokenConverter());
        }

        /**
         * 生成jwtToken的一些列处理
         * @return
         */
        @Bean
        public JwtAccessTokenConverter jwtAccessTokenConverter() {
            JwtAccessTokenConverter converter=new JwtAccessTokenConverter();
            converter.setSigningKey(signKey);
            return converter;
        }

        /**
         * 扩展jwtToken
         */
        @Bean
        @Primary
        public TokenEnhancer tokenEnhancer(){
            return new JwtTokenEnhancer();
        }


    }
}
