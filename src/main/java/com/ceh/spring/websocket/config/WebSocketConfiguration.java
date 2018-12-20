package com.ceh.spring.websocket.config;

import com.ceh.spring.websocket.message.websocket.CuxWebSocketHandler;
import com.ceh.spring.websocket.message.websocket.WebSocketInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.ExceptionWebSocketHandlerDecorator;

/**
 * Created by enHui.Chen on 2018/12/5.
 */
@Configuration
@EnableWebSocket
public class WebSocketConfiguration implements WebSocketConfigurer {
    @Autowired
    private CuxWebSocketHandler cuxWebSocketHandler;
    @Autowired
    private WebSocketInterceptor webSocketInterceptor;
    // 1.addHandler():配置handler的映射路径
    // 2.setAllowedOrigins():配置允许访问的主机(可以是域名(如果是域名需要带上https/http),ip,*(所有皆可访问))
    // 3.addInterceptors():添加拦截器
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(cuxWebSocketHandler, "/cux/**").setAllowedOrigins("*").addInterceptors(webSocketInterceptor);
    }

    @Bean
    public WebSocketHandler cuxExceptionWebSocketHandler() {
        return new ExceptionWebSocketHandlerDecorator(cuxWebSocketHandler);
    }
}
