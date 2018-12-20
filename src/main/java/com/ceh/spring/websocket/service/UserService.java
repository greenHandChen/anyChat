package com.ceh.spring.websocket.service;

import com.ceh.spring.websocket.dao.UserMapper;
import com.ceh.spring.websocket.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by enHui.Chen on 2018/12/20.
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User findUserByUsername(String username){
        return userMapper.findUserByUsername(username);
    }
}
