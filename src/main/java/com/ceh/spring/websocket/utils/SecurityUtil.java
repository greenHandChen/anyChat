package com.ceh.spring.websocket.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;

/**
 * Created by enHui.Chen on 2018/12/7.
 */
@Slf4j
public class SecurityUtil {

    public static String getUserName() {
        Authentication authentication;
        if ((authentication = SecurityContextHolder.getContext().getAuthentication()) != null) {
            if (authentication.getPrincipal() instanceof UserDetails) {
                return ((UserDetails) authentication.getPrincipal()).getUsername();
            }
        }
        return null;
    }
}
