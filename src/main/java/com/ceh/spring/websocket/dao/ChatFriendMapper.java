package com.ceh.spring.websocket.dao;

import com.ceh.spring.websocket.controller.dto.ChatFriendDTO;

import java.util.List;

/**
 * Created by enHui.Chen on 2018/12/20.
 */
public interface ChatFriendMapper {
    List<ChatFriendDTO> findByUsername(String username);
}
