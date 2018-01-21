package com.renault.dealer.router.handler;

import com.renault.dealer.router.config.ActiveMQConfig;
import com.renault.dealer.router.model.DealerSockectSession;
import com.renault.dealer.router.model.MessageRouted;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.support.JmsHeaders;
import org.springframework.jms.support.JmsMessageHeaderAccessor;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class OutMessageHandler {

    @Autowired
    HashMap<String, DealerSockectSession> DealerSockectSessions;


    private static final Logger log = LoggerFactory.getLogger(OutMessageHandler.class);

    @JmsListener(destination = ActiveMQConfig.OUT_QUEUE)
    public void receiveMessage(@Payload MessageRouted messageRouted,
                               @Header(JmsHeaders.CORRELATION_ID) String correlationId,
                               @Header(name = "jms-header-not-exists", defaultValue = "default") String nonExistingHeader,
                               @Headers Map<String, Object> headers,
                               MessageHeaders messageHeaders,
                               JmsMessageHeaderAccessor jmsMessageHeaderAccessor) throws IOException {

        log.info("received <" + messageRouted.toString() + ">");

        log.info("- jms_correlationId=" + correlationId);

        log.info("sending back with Send() to " + ActiveMQConfig.IN_QUEUE + " <" + messageRouted.toString() + ">");

        DealerSockectSession DmsWsSession = DealerSockectSessions.get(messageRouted.getUid());
        if (DmsWsSession != null) {
            WebSocketSession webSocketSession = DmsWsSession.getWebSocketSession();
            log.info("Sending Message on Socket # " + webSocketSession.getId() + "   UID : " + DmsWsSession.getUid());
            webSocketSession.sendMessage(new TextMessage(messageRouted.toString()));
        }
    }
}
