package com.ceh.spring.websocket.dao;

import com.ceh.spring.websocket.domain.User;

/**
 * Created by enHui.Chen on 2018/12/7.
 */
public interface UserMapper {
    User findUserByUsername(String username);
}
