package com.renault.drouter.DealerRouter.config;

import com.renault.drouter.DealerRouter.filter.WebSocketHttpSessionHandshakeInterceptor;
import com.renault.drouter.DealerRouter.handler.WebSSocketServerHandler;
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
        registry.addHandler(WebSSocketServerHandler(), "/ws")
                .addInterceptors(WebSocketHttpSessionHandshakeInterceptor());
    }

    @Bean
    public WebSocketHandler WebSSocketServerHandler() {
        return new WebSSocketServerHandler();
    }

    @Bean
    public HttpSessionHandshakeInterceptor WebSocketHttpSessionHandshakeInterceptor() {
        return new WebSocketHttpSessionHandshakeInterceptor();
    }

}