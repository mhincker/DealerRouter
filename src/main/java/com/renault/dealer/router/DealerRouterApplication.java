package com.renault.dealer.router;

import com.renault.dealer.router.model.Dealer;
import com.renault.dealer.router.repository.DealerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DealerRouterApplication {

	private static final Logger log = LoggerFactory.getLogger(DealerRouterApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(DealerRouterApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(DealerRepository repository) {
		return (args) -> {
			// save a couple of Clients
			repository.save(new Dealer("d0001", "Dealer1"));
			repository.save(new Dealer("d0002", "Dealer2"));
			repository.save(new Dealer("d0003", "Dealer3"));
			repository.save(new Dealer("d0004", "Dealer4"));
			repository.save(new Dealer("d0005", "Dealer5"));

			// fetch all Clients
			log.info("Dealers found with findAll():");
			log.info("-------------------------------");
			for (Dealer Dealer : repository.findAll()) {
				log.info(Dealer.toString());
			}
			log.info("");

			// fetch an individual Dealer by ID
			Dealer Dealer = repository.findOne(1L);
			log.info("Dealer found with findOne(1L):");
			log.info("--------------------------------");
			log.info(Dealer.toString());
			log.info("");

			// fetch Clients by last name
			log.info("Dealer found with findByLastName('Dealer3'):");
			log.info("--------------------------------------------");
			for (Dealer dealer3 : repository.findByUid("d0003")) {
				log.info(dealer3.toString());
			}
			log.info("");
		};
	}
}
