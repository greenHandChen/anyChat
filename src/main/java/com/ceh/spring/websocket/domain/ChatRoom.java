package com.ceh.spring.websocket.domain;

import lombok.Data;

import java.util.List;
import java.util.Date;

/**
 * Created by enHui.Chen on 2018/12/17.
 */
@Data
public class ChatRoom {

    private Long id;

    private String username;

    private List<String> usernames;

    private Date createdDate;

    private String status;

}
