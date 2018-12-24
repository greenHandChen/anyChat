package com.ceh.spring.websocket.controller;

import com.ceh.spring.websocket.controller.dto.ChatFriendDTO;
import com.ceh.spring.websocket.domain.ChatFriend;
import com.ceh.spring.websocket.service.ChatFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(value = "/getChatFriends", method = RequestMethod.GET)
    public ResponseEntity<List<ChatFriendDTO>> getChatFriends() {
        return new ResponseEntity<>(chatFriendService.findByUsername(), HttpStatus.OK);
    }

    @RequestMapping(value = "/searchUsers", method = RequestMethod.GET)
    public ResponseEntity<List<ChatFriendDTO>> searchUsers(String username) {
        return new ResponseEntity<>(chatFriendService.searchUsers(username), HttpStatus.OK);
    }
}
