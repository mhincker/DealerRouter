package com.renault.dealer.router.web;

import com.renault.dealer.router.DealerRouterApplication;
import com.renault.dealer.router.model.MessageRouted;
import com.renault.dealer.router.service.MessageSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DealerController {

    private static Logger log = LoggerFactory.getLogger(DealerRouterApplication.class);

    @Autowired
    private MessageSender messageSender;

    @RequestMapping("/dealers/router")
    public MessageRouted incomingMessage(@RequestParam(value="json", defaultValue="") String json) {

        log.info("Setting and Reading Spring JMS Message Header Properties Example");
        return messageSender.sendQueue(new MessageRouted("p014322", "BVM", new String("De Renault")));
    }

}
