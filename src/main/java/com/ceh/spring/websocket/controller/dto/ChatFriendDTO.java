package com.ceh.spring.websocket.controller.dto;

import lombok.Data;

import java.util.Date;

/**
 * Created by enHui.Chen on 2018/12/20.
 */
@Data
public class ChatFriendDTO {

    private Long id;

    private Long userId;

    private Long friendId;

    private Long launchId;

    private boolean status;

    private Date createdDate;

    private String fullName;

}
