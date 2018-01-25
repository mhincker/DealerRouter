package com.renault.dealer.router.handler;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.renault.dealer.router.config.ActiveMQConfig;
import com.renault.dealer.router.helper.MessageHelper;
import com.renault.dealer.router.model.DealerSockectSession;
import com.renault.dealer.router.model.MessageRouted;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;

public class WebSSocketServerHandler extends TextWebSocketHandler {

    @Autowired
    HashMap<String, DealerSockectSession> dealerSockectSessionHashMap;

    @Autowired
    private JmsTemplate jmsTemplate;

    private static final Logger log = LoggerFactory.getLogger(OutMessageHandler.class);


    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        log.info("afterConnectionEstablished");
        DealerSockectSession dealerSockectSession = new DealerSockectSession("p014322", "", session);
        //dealerSockectSession.put("p014322", dealerSockectSession);
        log.info("DealerSockectSessions Add : " + session.getId());
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {

        log.info("WebSSocketServerHandler handleMessage : " + message.getPayload());
        ObjectMapper objectMapper = new ObjectMapper();
        try {
        TextMessage messageString = objectMapper.readValue(message.getPayload(), TextMessage.class);
        log.info(String.format("WebSSocketServerHandler messageString : %s", messageString));
        MessageRouted messageRouted = objectMapper.readValue(message.getPayload(), MessageRouted.class);
        MessageHelper messageHelper = new MessageHelper();
        messageHelper.SendMessage(jmsTemplate, ActiveMQConfig.IN_QUEUE, messageRouted, messageRouted.getCorrelationId());
        } catch (IOException e) {
            log.error("Mapper MessageRouted failed : " + e);
        }
    }
}