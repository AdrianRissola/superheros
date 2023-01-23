package com.w2m.superheros;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.w2m.superheros.adapters.out.jpa.SuperheroJpaRepository;
import com.w2m.superheros.adapters.out.jpa.entities.PowerJpaEntity;
import com.w2m.superheros.adapters.out.jpa.entities.SuperheroJpaEntity;
import com.w2m.superheros.application.ports.in.SuperheroService;

@Configuration
@Profile({ "prod" })
public class LoadDatabase {
	
	private static final Logger logger = LoggerFactory.getLogger(LoadDatabase.class);
	
	@Bean
	CommandLineRunner initDatabase(SuperheroService service, SuperheroJpaRepository superheroRepository) {
		
		// Ironman
		PowerJpaEntity fly = new PowerJpaEntity("fly", 50, true);
		PowerJpaEntity laser = new PowerJpaEntity("laser", 50, true);
		SuperheroJpaEntity ironman = new SuperheroJpaEntity("Ironman", "Tony Stark ", true);
		fly.setSuperhero(ironman);
		laser.setSuperhero(ironman);
		List<PowerJpaEntity> ironmanPowers = new ArrayList<PowerJpaEntity>();
		ironmanPowers.add(fly);
		ironmanPowers.add(laser);
		ironman.setPowers(ironmanPowers);
		
		// Spiderman
		PowerJpaEntity jump = new PowerJpaEntity("super jump", 50, true);
		SuperheroJpaEntity spiderman = new SuperheroJpaEntity("Spiderman", "Peter Parker", true);
		jump.setSuperhero(spiderman);
		List<PowerJpaEntity> spidermanPowers = new ArrayList<PowerJpaEntity>();
		spidermanPowers.add(jump);
		spiderman.setPowers(spidermanPowers);
		
		// Superman
		List<SuperheroJpaEntity> superheros = new ArrayList<>();
		for(int i=1 ; i<=10000 ; i++) {
			PowerJpaEntity fly2 = new PowerJpaEntity("fly", i, true);
			PowerJpaEntity laserEyes2 = new PowerJpaEntity("laser eyes", i, true);
			SuperheroJpaEntity superman = new SuperheroJpaEntity("Superman", "Clark Kent", false);
			fly2.setSuperhero(superman);
			laserEyes2.setSuperhero(superman);
			List<PowerJpaEntity> supermanPowers2 = new ArrayList<PowerJpaEntity>();
			supermanPowers2.add(fly2);
			supermanPowers2.add(laserEyes2);
			superman.setPowers(supermanPowers2);
			superheros.add(superman);
		}
		
		return args -> {
			logger.info("Preloading: {}", superheroRepository.save(ironman));
			logger.info("Preloading: {}", superheroRepository.save(spiderman));
			logger.info("Preloading: {}", superheroRepository.save(new SuperheroJpaEntity("Batman", "Bruce Wayne", true)));
			logger.info("Preloading: {}", superheroRepository.save(new SuperheroJpaEntity("Robocop", "Alex Murphy", true)));
			logger.info("Preloading: {}", superheroRepository.saveAll(superheros).size());
		};
	}

}
