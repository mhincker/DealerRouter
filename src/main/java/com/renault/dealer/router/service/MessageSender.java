package com.renault.dealer.router.service;

import com.renault.dealer.router.config.ActiveMQConfig;
import com.renault.dealer.router.model.MessageRouted;
import org.apache.activemq.command.ActiveMQQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.DeliveryMode;
import javax.jms.Message;
import java.util.UUID;

@Service
public class MessageSender {

    private static Logger log = LoggerFactory.getLogger(MessageSender.class);

    @Autowired
    private JmsTemplate jmsTemplate;
    private String correlationId = UUID.randomUUID().toString();

    public MessageRouted sendQueue(MessageRouted messageRouted) {
        log.info("sending with convertAndSend() to " + ActiveMQConfig.OUT_QUEUE + " <" + messageRouted + ">");
        jmsTemplate.convertAndSend(ActiveMQConfig.OUT_QUEUE, messageRouted, m -> {

            log.info("setting standard JMS headers before sending");
            m.setJMSCorrelationID(correlationId);
            m.setJMSExpiration(1000);
            m.setJMSMessageID(correlationId);
            m.setJMSDestination(new ActiveMQQueue(ActiveMQConfig.OUT_QUEUE));
            m.setJMSReplyTo(new ActiveMQQueue(ActiveMQConfig.IN_QUEUE));
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
        jmsTemplate.setReceiveTimeout(3000);
        String selector = "JMSCorrelationID = '" + correlationId  + "'";
        try {
            MessageRouted DMSResponse = (MessageRouted) jmsTemplate.receiveSelectedAndConvert(ActiveMQConfig.IN_QUEUE,selector);
            log.info("Message de la QUEUE IN" + DMSResponse.toString());
            return DMSResponse;
        }
        catch (Exception e){
            log.error("Message Not Retrived in IN QUEUE");
            return null;
        }
    }

}
