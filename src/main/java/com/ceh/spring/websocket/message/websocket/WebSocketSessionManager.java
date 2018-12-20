package com.ceh.spring.websocket.message.websocket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by enHui.Chen on 2018/12/5.
 */
@Component
public class WebSocketSessionManager {
    private static final ConcurrentHashMap<String, ChatWebSocketSession> sessionMap = new ConcurrentHashMap<>();

    public ChatWebSocketSession getSession(WebSocketSession webSocketSession) {
        return webSocketSession != null ? sessionMap.get(webSocketSession.getId()) : null;
    }

    public void removeSession(WebSocketSession webSocketSession) {
        if (getSession(webSocketSession) != null) {
            sessionMap.remove(webSocketSession.getId());
        }
    }

    public void addSession(ChatWebSocketSession webSocketSession) {
        if (webSocketSession != null && webSocketSession.getWebSocketSession() != null) {
            sessionMap.put(webSocketSession.getWebSocketSession().getId(), webSocketSession);
        }
    }

    public void close(WebSocketSession webSocketSession) throws IOException {
        this.removeSession(webSocketSession);
        if (webSocketSession.isOpen()) {
            webSocketSession.close();
        }
    }

    public static ConcurrentHashMap<String, ChatWebSocketSession> getAllSessions() {
        return sessionMap;
    }
}
