package com.gf.oauth2.server.feign;

import com.gf.oauth2.common.result.BaseResponse;
import com.gf.oauth2.server.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "oauth2-user")
public interface IUserService {
    @GetMapping("users")
    BaseResponse<User> queryUserByname(@RequestParam("username") String username);

}
