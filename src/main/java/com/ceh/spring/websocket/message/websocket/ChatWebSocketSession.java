package com.ceh.spring.websocket.message.websocket;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.socket.WebSocketSession;

/**
 * Created by enHui.Chen on 2018/12/7.
 */
@Data
@Builder
public class ChatWebSocketSession{
    private WebSocketSession webSocketSession;

    private String username;
}
