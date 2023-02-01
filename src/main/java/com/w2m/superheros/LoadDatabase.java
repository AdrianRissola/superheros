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

@Configuration
@Profile({ "prod" })
public class LoadDatabase {
	
	private static final Logger logger = LoggerFactory.getLogger(LoadDatabase.class);
	
	@Bean
	CommandLineRunner initDatabase(SuperheroJpaRepository superheroRepository) {
		
		// Ironman
		var fly = new PowerJpaEntity("fly", 50, true);
		var laser = new PowerJpaEntity("laser", 50, true);
		var ironman = new SuperheroJpaEntity("Ironman", "Tony Stark ", true);
		var ironmanPowers = new ArrayList<PowerJpaEntity>();
		ironmanPowers.add(fly);
		ironmanPowers.add(laser);
		ironman.setPowers(ironmanPowers);
		
		// Spiderman
		var jump = new PowerJpaEntity("super jump", 50, true);
		var spiderman = new SuperheroJpaEntity("Spiderman", "Peter Parker", true);
		var spidermanPowers = new ArrayList<PowerJpaEntity>();
		spidermanPowers.add(jump);
		spiderman.setPowers(spidermanPowers);
		
		// Superman
		var superheros = new ArrayList<SuperheroJpaEntity>();
		for(var i=1 ; i<=10000 ; i++) {
			var fly2 = new PowerJpaEntity("fly", i, true);
			var laserEyes2 = new PowerJpaEntity("laser eyes", i, true);
			var superman = new SuperheroJpaEntity("Superman", "Clark Kent", false);
			var supermanPowers2 = new ArrayList<PowerJpaEntity>();
			supermanPowers2.add(fly2);
			supermanPowers2.add(laserEyes2);
			superman.setPowers(supermanPowers2);
			superheros.add(superman);
		}
		
		return args -> {
			logger.info("Preloading: {}", superheroRepository.save(ironman));
			logger.info("Preloading: {}", superheroRepository.save(spiderman));
			logger.info("Preloading: {}", superheroRepository.saveAll(superheros).size());
		};
	}

}
