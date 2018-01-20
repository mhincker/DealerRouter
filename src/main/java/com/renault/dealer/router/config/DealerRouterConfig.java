package com.renault.dealer.router.config;

import com.renault.dealer.router.model.DealerSockectSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class DealerRouterConfig {

    @Bean
    public HashMap<String, DealerSockectSession> DealerSockectSessions (){
      return new HashMap<String, DealerSockectSession>();
    }

}
