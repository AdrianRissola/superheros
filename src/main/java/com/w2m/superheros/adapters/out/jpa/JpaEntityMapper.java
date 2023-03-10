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
		var superheros = new ArrayList<Superhero>();
		superheroJpaEntities.stream().forEach(superheroJpaEntity -> superheros.add(fromJpaEntity(superheroJpaEntity)));
		return superheros;
	}

	public static Superhero fromJpaEntity(SuperheroJpaEntity superheroJpaEntity) {
		Superhero superhero = null;
		if(superheroJpaEntity!=null) {
			superhero = new Superhero.Builder(superheroJpaEntity.getName(), 
					superheroJpaEntity.getRealFullName(), superheroJpaEntity.isHumanBeing(), 
					fromPowerJpaEntities(superheroJpaEntity.getPowers())).withId(superheroJpaEntity.getId()).build();
		}
		return superhero;
	}

	private static List<Power> fromPowerJpaEntities(List<PowerJpaEntity> powerJpaEntities) {
		var powers = new ArrayList<Power>();
		powerJpaEntities.stream().forEach(powerJpaEntity -> powers.add(fromJpaEntity(powerJpaEntity)));
		return powers;
	}

	private static Power fromJpaEntity(PowerJpaEntity powerJpaEntity) {
		var power = new Power.Builder(powerJpaEntity.getName(), powerJpaEntity.getIntensity(),
				powerJpaEntity.isEnabled()).withId(powerJpaEntity.getId()).build();
		return power;
	}

	public static SuperheroJpaEntity toJpaEntity(Superhero superhero) {
		var superheroJpaEntity = new SuperheroJpaEntity();
		BeanUtils.copyProperties(superhero, superheroJpaEntity);
		superheroJpaEntity.setPowers(toJpaEntities(superhero.getPowers()));
		return superheroJpaEntity;
	}

	private static List<PowerJpaEntity> toJpaEntities(List<Power> powers) {
		var powerJpaEntities = new ArrayList<PowerJpaEntity>();
		powers.stream().forEach(power -> powerJpaEntities.add(toJpaEntity(power)));
		return powerJpaEntities;
	}

	private static PowerJpaEntity toJpaEntity(Power power) {
		var powerJpaEntity = new PowerJpaEntity();
		BeanUtils.copyProperties(power, powerJpaEntity);
		return powerJpaEntity;
	}

}
