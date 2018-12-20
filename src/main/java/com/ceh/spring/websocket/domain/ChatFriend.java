package com.ceh.spring.websocket.domain;

import lombok.Data;

import java.util.Date;

/**
 * Created by enHui.Chen on 2018/12/20.
 */
@Data
public class ChatFriend {

    private Long id;

    private Long userId;

    private Long friendId;

    private Long launchId;

    private boolean status;

    private Date createdDate;

}
