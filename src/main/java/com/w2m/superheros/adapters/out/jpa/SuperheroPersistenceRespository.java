package com.w2m.superheros.adapters.out.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.w2m.superheros.adapters.out.jpa.entities.SuperheroJpaEntity;
import com.w2m.superheros.application.model.entities.Superhero;
import com.w2m.superheros.application.ports.out.SuperheroRepository;

@Service("superheroPersistenceRespository")
public class SuperheroPersistenceRespository implements SuperheroRepository {
	
	@Autowired
	private SuperheroJpaRepository superheroJpaRepository;

	@Override
	public List<Superhero> findAll() {
		List<SuperheroJpaEntity> superheros = this.superheroJpaRepository.findAll();
		return JpaEntityMapper.fromSuperheroJpaEntities(superheros);
	}

	@Override
	public List<Superhero> findByNameContains(String param) {
		List<SuperheroJpaEntity> superheros = this.superheroJpaRepository.findByNameContainingIgnoreCase(param.strip());
		return JpaEntityMapper.fromSuperheroJpaEntities(superheros);
	}

	@Override
	public Superhero findById(int id) {
		SuperheroJpaEntity superheroJpaEntity = this.superheroJpaRepository.findById(id).orElse(null);
		return JpaEntityMapper.fromJpaEntity(superheroJpaEntity);
	}

	@Override
	public Superhero update(Superhero superhero) {
		SuperheroJpaEntity superheroJpaEntity = this.superheroJpaRepository.save(JpaEntityMapper.toJpaEntity(superhero));
		return JpaEntityMapper.fromJpaEntity(superheroJpaEntity);
	}

	@Override
	public Superhero delete(Superhero superhero) {
		this.superheroJpaRepository.delete(JpaEntityMapper.toJpaEntity(superhero));
		return superhero;
	}

}
