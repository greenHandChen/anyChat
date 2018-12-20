package com.ceh.spring.websocket.service;

import com.ceh.spring.websocket.controller.dto.ChatFriendDTO;
import com.ceh.spring.websocket.dao.ChatFriendMapper;
import com.ceh.spring.websocket.domain.ChatFriend;
import com.ceh.spring.websocket.utils.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by enHui.Chen on 2018/12/20.
 */
@Service
public class ChatFriendService {
    @Autowired
    private ChatFriendMapper chatFriendMapper;

    public List<ChatFriendDTO> findByUsername(){
        return chatFriendMapper.findByUsername(SecurityUtil.getUserName());
    }
}
