package com.gf.oauth2.server.customImpl;

import com.gf.oauth2.common.result.BaseResponse;
import com.gf.oauth2.server.entity.User;
import com.gf.oauth2.server.feign.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserService userService;
    Logger log=LoggerFactory.getLogger(MyUserDetailsService.class);
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        BaseResponse<User> baseResponse = userService.queryUserByname(username);
        if (baseResponse.getStatus().equals(200)){
            User user=baseResponse.getData();
            if (user==null){
                throw new UsernameNotFoundException("用户未找到");
            }
            return new org.springframework.security.core.userdetails.User(username,"{bcrypt}"+user.getPassword(),
                    true,true,true,true,
                    AuthorityUtils.commaSeparatedStringToAuthorityList("admin,ROLE_USER"));
        }
        log.error("用户信息未找到:"+username);
        throw new UsernameNotFoundException("用户信息不匹配");
    }
}
