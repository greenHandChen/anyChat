package com.ceh.spring.websocket.security;


import com.ceh.spring.websocket.dao.UserMapper;
import com.ceh.spring.websocket.domain.User;
import com.ceh.spring.websocket.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by enHui.Chen on 2018/4/10.
 */
@Slf4j
@Component
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private UserService userService;
//    @Autowired
//    private RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findUserByUsername(username);
        log.info("username:{}",username);
        log.info("password:{}",user.getPassword());
        if (user == null) {
            throw new UsernameNotFoundException("未找到该用户！");
        }
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        simpleGrantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), simpleGrantedAuthorities);
    }

}
