package com.ceh.spring.websocket.domain;

import lombok.Data;

import java.util.Date;

/**
 * Created by enHui.Chen on 2018/12/7.
 */
@Data
public class User {

    private Long id;

    private String username;

    private String password;

    private String fullName;

    private String gender;

    private Date birthday;

    private String email;

    private String mobile;

    private String address;

    private Date lastLoginDate;

    private boolean activated;

    private String status;

    private Date createdDate;
}
