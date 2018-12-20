package com.ceh.spring.websocket.controller;

import com.ceh.spring.websocket.controller.dto.ChatFriendDTO;
import com.ceh.spring.websocket.domain.ChatFriend;
import com.ceh.spring.websocket.service.ChatFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by enHui.Chen on 2018/12/20.
 */
@RestController
@RequestMapping("/api")
public class ChatFriendController {
    @Autowired
    private ChatFriendService chatFriendService;

    @RequestMapping(value = "/getChatFriends")
    public ResponseEntity<List<ChatFriendDTO>> findByUsername(){
        return new ResponseEntity<>(chatFriendService.findByUsername(), HttpStatus.OK);
    }
}
