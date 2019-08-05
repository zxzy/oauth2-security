package com.gf.oauth2.user.service.impl;

import com.gf.oauth2.user.entity.User;
import com.gf.oauth2.user.mapper.UserMapper;
import com.gf.oauth2.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

@Service
public class UserServiceImpl implements UserService {

    private BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    @Autowired
    private UserMapper userMapper;
    @Override
    public int createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.insert(user);
    }

    @Override
    public User queryUserByName(String name) {
        Example example=new Example(User.class);
        Example.Criteria criteria=example.createCriteria();
        criteria.andEqualTo("username",name);
        return userMapper.selectOneByExample(example);
    }
}
