package com.gf.oauth2.user.service;


import com.gf.oauth2.user.entity.User;

public interface UserService {

    int createUser(User user);

    User queryUserByName(String name);
}
