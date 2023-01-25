package com.w2m.superheros;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import com.w2m.superheros.adapters.out.jpa.SuperheroJpaRepository;
import com.w2m.superheros.adapters.out.jpa.entities.PowerJpaEntity;
import com.w2m.superheros.adapters.out.jpa.entities.SuperheroJpaEntity;
import com.w2m.superheros.application.ports.in.SuperheroService;

@TestConfiguration
@Profile({ "test" })
public class LoadTestingDatabase {
	
	private static final Logger logger = LoggerFactory.getLogger(LoadTestingDatabase.class);
	
	@Bean
	CommandLineRunner initTestingDatabase(SuperheroService service, SuperheroJpaRepository superheroRepository) {
		PowerJpaEntity nightVision = new PowerJpaEntity("Night-Vision", 50, true);
		PowerJpaEntity webSlinging = new PowerJpaEntity("Web-Slinging", 50, true);
		SuperheroJpaEntity spiderman = new SuperheroJpaEntity("Spiderman", "Peter Parker", true);
		List<PowerJpaEntity> spidermanPowers = new ArrayList<PowerJpaEntity>();
		spidermanPowers.add(nightVision);
		spidermanPowers.add(webSlinging);
		spiderman.setPowers(spidermanPowers);
		return args -> {
			logger.info("Preloading tsting database with: {}", superheroRepository.save(spiderman));
		};
	}

}
