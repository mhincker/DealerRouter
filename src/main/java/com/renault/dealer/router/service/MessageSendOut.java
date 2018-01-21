package com.renault.dealer.router.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.renault.dealer.router.config.ActiveMQConfig;
import com.renault.dealer.router.helper.MessageHelper;
import com.renault.dealer.router.model.MessageRouted;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;


@Service
public class MessageSendOut {

    private static final Logger log = LoggerFactory.getLogger(MessageSendOut.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    public MessageRouted sendQueue(MessageRouted messageRouted) {
        ObjectMapper objectMapper = new ObjectMapper();

        log.info("sending with convertAndSend() to " + ActiveMQConfig.OUT_QUEUE + " <" + messageRouted + ">");

        MessageHelper messageHelper = new MessageHelper();
        messageHelper.SendMessage(jmsTemplate,ActiveMQConfig.OUT_QUEUE, messageRouted, messageRouted.getCorrelationId());

        jmsTemplate.setReceiveTimeout(3000);
        String selector = "JMSCorrelationID = '" + messageRouted.getCorrelationId()  + "'";
        try {
            log.info("Message in JSON : " +objectMapper.writeValueAsString(messageRouted));
            MessageRouted DMSResponse = (MessageRouted) jmsTemplate.receiveSelected(ActiveMQConfig.IN_QUEUE,selector);
            log.info("Message de la QUEUE IN :" + DMSResponse.toString());
            return DMSResponse;
        }
        catch (Exception e){
            log.error("Message Not Retrived in IN QUEUE");
            return null;
        }
    }

}
