package com.renault.dealer.router.handler;

import com.renault.dealer.router.model.DealerSockectSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;

public class WebSSocketServerHandler implements WebSocketHandler {

    @Autowired
    HashMap<String, DealerSockectSession> DealerSockectSessions;

    private static Logger log = LoggerFactory.getLogger(OutMessageHandler.class);


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("afterConnectionEstablished");
        DealerSockectSession dealerSockectSession = new DealerSockectSession("p014322","",session);
        DealerSockectSessions.put("p014322",dealerSockectSession);
        log.info("DealerSockectSessions Add : "+ session.getId());
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        log.info("WebSSocketServerHandler new message : "+ message.getPayload().toString());

    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {

    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
