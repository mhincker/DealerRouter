package com.renault.dealer.router.web;

import com.renault.dealer.router.DealerRouterApplication;
import com.renault.dealer.router.model.MessageRouted;
import com.renault.dealer.router.service.MessageSendOut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class DealerController {

    private static final Logger log = LoggerFactory.getLogger(DealerRouterApplication.class);
    private final String correlationId = UUID.randomUUID().toString();

    @Autowired
    private MessageSendOut messageSendOut;

    @RequestMapping("/dealers/router")
    public MessageRouted incomingMessage(@RequestParam(value="json", defaultValue="") String json) {

        log.info("Setting and Reading Spring JMS Message Header Properties Example");
        return messageSendOut.sendQueue(new MessageRouted("p014322", "Dealer",correlationId ,"BVM",new String("De Renault")));
    }

}
