package com.w2m.superheros.adapters.out.jpa;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.w2m.superheros.adapters.out.jpa.entities.PowerJpaEntity;
import com.w2m.superheros.adapters.out.jpa.entities.SuperheroJpaEntity;
import com.w2m.superheros.application.model.entities.Power;
import com.w2m.superheros.application.model.entities.Superhero;

public class JpaEntityMapper {



	public static List<Superhero> fromSuperheroJpaEntities(List<SuperheroJpaEntity> superheroJpaEntities) {
		List<Superhero> superheros = new ArrayList<>();
		superheroJpaEntities.stream().forEach(superheroJpaEntity -> superheros.add(fromJpaEntity(superheroJpaEntity)));
		return superheros;
	}

	private static Superhero fromJpaEntity(SuperheroJpaEntity superheroJpaEntity) {
		Superhero superhero = new Superhero();
		BeanUtils.copyProperties(superheroJpaEntity, superhero);
		superhero.setPowers(fromPowerJpaEntities(superheroJpaEntity.getPowers()));
		return superhero;
	}

	private static List<Power> fromPowerJpaEntities(List<PowerJpaEntity> powerJpaEntities) {
		List<Power> powers = new ArrayList<>();
		powerJpaEntities.stream().forEach(powerJpaEntity -> powers.add(fromJpaEntity(powerJpaEntity)));
		return powers;
	}

	private static Power fromJpaEntity(PowerJpaEntity powerJpaEntity) {
		Power power = new Power();
		BeanUtils.copyProperties(powerJpaEntity, power);
		return power;
	}





}
