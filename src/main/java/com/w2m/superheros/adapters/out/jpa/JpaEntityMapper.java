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

	public static Superhero fromJpaEntity(SuperheroJpaEntity superheroJpaEntity) {
		Superhero superhero = null;
		if(superheroJpaEntity!=null) {
			superhero = new Superhero();
			BeanUtils.copyProperties(superheroJpaEntity, superhero);
			superhero.setPowers(fromPowerJpaEntities(superheroJpaEntity.getPowers()));
		}
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

	public static SuperheroJpaEntity toJpaEntity(Superhero superhero) {
		SuperheroJpaEntity superheroJpaEntity = new SuperheroJpaEntity();
		BeanUtils.copyProperties(superhero, superheroJpaEntity);
		superheroJpaEntity.setPowers(toJpaEntities(superhero.getPowers()));
		return superheroJpaEntity;
	}

	private static List<PowerJpaEntity> toJpaEntities(List<Power> powers) {
		List<PowerJpaEntity> powerJpaEntities = new ArrayList<>();
		powers.stream().forEach(power -> powerJpaEntities.add(toJpaEntity(power)));
		return powerJpaEntities;
	}

	private static PowerJpaEntity toJpaEntity(Power power) {
		PowerJpaEntity powerJpaEntity = new PowerJpaEntity();
		BeanUtils.copyProperties(power, powerJpaEntity);
		return powerJpaEntity;
	}





}
