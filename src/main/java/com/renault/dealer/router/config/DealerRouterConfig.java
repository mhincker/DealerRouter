package com.renault.dealer.router.config;

import com.renault.dealer.router.model.DealerSockectSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class DealerRouterConfig {

    @Bean
    public Map<String, DealerSockectSession> dealerSockectSessionMap (){
      return new HashMap<>();
    }

}
