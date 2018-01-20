package com.renault.dealer.router.handler;

import com.renault.dealer.router.config.ActiveMQConfig;
import com.renault.dealer.router.model.DealerSockectSession;
import com.renault.dealer.router.model.MessageRouted;
import org.apache.activemq.command.ActiveMQQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.JmsHeaders;
import org.springframework.jms.support.JmsMessageHeaderAccessor;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.jms.DeliveryMode;
import javax.jms.Message;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class OutMessageHandler {

    @Autowired
    private JmsTemplate jmsTemplate;


    @Autowired
    HashMap<String, DealerSockectSession> DealerSockectSessions;

    private static Logger log = LoggerFactory.getLogger(OutMessageHandler.class);

    @JmsListener(destination = ActiveMQConfig.OUT_QUEUE)
    public void receiveMessage(@Payload MessageRouted MessageRouted,
                               @Header(JmsHeaders.CORRELATION_ID) String correlationId,
                               @Header(name = "jms-header-not-exists", defaultValue = "default") String nonExistingHeader,
                               @Headers Map<String, Object> headers,
                               MessageHeaders messageHeaders,
                               JmsMessageHeaderAccessor jmsMessageHeaderAccessor) throws IOException {

        log.info("received <" + MessageRouted + ">");

        log.info("\n# Spring JMS accessing single header property");
        log.info("- jms_correlationId=" + correlationId);
        log.info("- jms-header-not-exists=" + nonExistingHeader);

        log.info("\n# Spring JMS retrieving all header properties using Map<String, Object>");
        log.info("- jms-custom-header=" + String.valueOf(headers.get("jms-custom-property")));

        log.info("\n# Spring JMS retrieving all header properties MessageHeaders");
        log.info("- jms-custom-property-price=" + messageHeaders.get("jms-custom-property-price", Double.class));

        log.info("\n# Spring JMS retrieving all header properties JmsMessageHeaderAccessor");
        log.info("- jms_destination=" + jmsMessageHeaderAccessor.getDestination());
        log.info("- jms_priority=" + jmsMessageHeaderAccessor.getPriority());
        log.info("- jms_timestamp=" + jmsMessageHeaderAccessor.getTimestamp());
        log.info("- jms_type=" + jmsMessageHeaderAccessor.getType());
        log.info("- jms_redelivered=" + jmsMessageHeaderAccessor.getRedelivered());
        log.info("- jms_replyTo=" + jmsMessageHeaderAccessor.getReplyTo());
        log.info("- jms_correlationId=" + jmsMessageHeaderAccessor.getCorrelationId());
        log.info("- jms_contentType=" + jmsMessageHeaderAccessor.getContentType());
        log.info("- jms_expiration=" + jmsMessageHeaderAccessor.getExpiration());
        log.info("- jms_messageId=" + jmsMessageHeaderAccessor.getMessageId());
        log.info("- jms_deliveryMode=" + jmsMessageHeaderAccessor.getDeliveryMode() + "\n");

        log.info("sending back with convertAndSend() to " + ActiveMQConfig.IN_QUEUE + " <" + MessageRouted + ">");

        DealerSockectSession DmsWsSession = DealerSockectSessions.get(MessageRouted.getUid());
        if (DmsWsSession != null){
            WebSocketSession webSocketSession = DmsWsSession.getWebSocketSession();
            log.info("Sending Message on Socket # " + webSocketSession.getId()+ "   UID : "+DmsWsSession.getUid());
            webSocketSession.sendMessage(new TextMessage(MessageRouted.toString()));
        }


        jmsTemplate.convertAndSend(ActiveMQConfig.IN_QUEUE, MessageRouted, m -> {

            log.info("setting standard JMS headers before sending");
            m.setJMSCorrelationID(correlationId);
            m.setJMSExpiration(1000);
            m.setJMSMessageID(correlationId);
            m.setJMSDestination(new ActiveMQQueue(ActiveMQConfig.IN_QUEUE));
            m.setJMSDeliveryMode(DeliveryMode.NON_PERSISTENT);
            m.setJMSPriority(Message.DEFAULT_PRIORITY);
            m.setJMSTimestamp(System.nanoTime());
            m.setJMSType("type");

            log.info("setting custom JMS headers before sending");
            m.setStringProperty("jms-custom-header", "this is a custom jms property");
            m.setBooleanProperty("jms-custom-property", true);
            m.setDoubleProperty("jms-custom-property-price", 0.0);

            return m;
        });
    }

}
