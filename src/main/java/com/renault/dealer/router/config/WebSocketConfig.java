package com.renault.dealer.router.config;

import com.renault.dealer.router.filter.WebSocketHttpSessionHandshakeInterceptor;
import com.renault.dealer.router.handler.WebSSocketServerHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSSocketServerHandler(), "/ws")
                .addInterceptors(webSocketHttpSessionHandshakeInterceptor());
    }

    @Bean
    public WebSocketHandler webSSocketServerHandler() {
        return new WebSSocketServerHandler();
    }

    @Bean
    public HttpSessionHandshakeInterceptor webSocketHttpSessionHandshakeInterceptor() {
        return new WebSocketHttpSessionHandshakeInterceptor();
    }

}