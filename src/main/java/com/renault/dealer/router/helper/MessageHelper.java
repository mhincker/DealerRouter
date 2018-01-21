package com.renault.dealer.router.helper;

import com.renault.dealer.router.handler.OutMessageHandler;
import com.renault.dealer.router.model.MessageRouted;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;


import javax.jms.DeliveryMode;
import javax.jms.Message;

@Component
public class MessageHelper {

    private static final Logger log = LoggerFactory.getLogger(OutMessageHandler.class);

    public void SendMessage (JmsTemplate jmsTemplate,String destination, MessageRouted messageRouted, String correlationId) {
            jmsTemplate.convertAndSend(destination, messageRouted, m -> {
                log.info("setting standard JMS headers before sending");
                m.setJMSCorrelationID(correlationId);
                m.setJMSExpiration(10000);
                m.setJMSMessageID(correlationId);
                m.setJMSDeliveryMode(DeliveryMode.NON_PERSISTENT);
                m.setJMSPriority(Message.DEFAULT_PRIORITY);
                m.setJMSTimestamp(System.nanoTime());
                m.setJMSType("type");

                return m;
                });
        }
}
