package com.gf.oauth2.user.controller;

import com.gf.oauth2.common.result.BaseResponse;
import com.gf.oauth2.user.entity.User;
import com.gf.oauth2.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public BaseResponse createUser(@RequestBody User user){
        userService.createUser(user);
        return new BaseResponse();
    }

    @GetMapping
    public BaseResponse<User> queryUserByName(@RequestParam("username") String username){
        User user = userService.queryUserByName(username);
        return new BaseResponse<>(user);
    }
}
