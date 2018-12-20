package com.ceh.spring.websocket.message.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by enHui.Chen on 2018/12/5.
 */
@Slf4j
@Component
public class CuxWebSocketHandler extends TextWebSocketHandler {
    @Autowired
    private WebSocketSessionManager webSocketSessionManager;

    // 建立连接后
    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        log.info("CuxWebSocketHandler is connected......sessionId={}......userName={}", webSocketSession.getId(), webSocketSession.getAttributes().get("username"));
        webSocketSessionManager.addSession(ChatWebSocketSession
                .builder()
                .webSocketSession(webSocketSession)
                .username((String) webSocketSession.getAttributes().get("username"))
                .build());
    }

    // 发送消息
    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
        log.info("webSocketMessage:{}", webSocketMessage.getPayload());
        for (Map.Entry<String, ChatWebSocketSession> sessionEntry : WebSocketSessionManager.getAllSessions().entrySet()) {
            ChatWebSocketSession chatWebSocketSession = sessionEntry.getValue();
            StringBuilder sb = new StringBuilder("{\"data\":\"");
            String isSelf = webSocketSession.getId().equals(chatWebSocketSession.getWebSocketSession().getId()) ? "\"right\"" : "\"left\"";
            chatWebSocketSession.getWebSocketSession().sendMessage(new TextMessage(sb
                    .append(webSocketSessionManager.getSession(webSocketSession).getUsername())
                    .append(":")
                    .append(webSocketMessage.getPayload())
                    .append("\",\"chatPosition\":")
                    .append(isSelf).append("}").toString()));
        }

    }

    // 异常处理
    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
        log.info("CuxWebSocketHandler is error......sessionId={}......userName={}", webSocketSession.getId(), webSocketSessionManager.getSession(webSocketSession).getUsername());
        webSocketSessionManager.close(webSocketSession);
    }

    // 关闭连接后
    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        log.info("CuxWebSocketHandler is closed......sessionId={}......userName={}......closeStatus={}", webSocketSession.getId(), webSocketSessionManager.getSession(webSocketSession).getUsername(), closeStatus.getCode());
        webSocketSessionManager.removeSession(webSocketSession);
    }

    // 是否支持消息的部分处理,true支持,false不支持
    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
