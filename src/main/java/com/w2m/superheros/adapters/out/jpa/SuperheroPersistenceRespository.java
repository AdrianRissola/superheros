package com.w2m.superheros.adapters.out.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.w2m.superheros.application.model.entities.Superhero;
import com.w2m.superheros.application.model.exceptions.ErrorMessage;
import com.w2m.superheros.application.model.exceptions.SuperheroException;
import com.w2m.superheros.application.ports.out.SuperheroRepository;

@Service("superheroPersistenceRespository")
public class SuperheroPersistenceRespository implements SuperheroRepository {
	
	@Autowired
	private SuperheroJpaRepository superheroJpaRepository;

	@Override
	public List<Superhero> findAll() {
		var superheros = this.superheroJpaRepository.findAll();
		return JpaEntityMapper.fromSuperheroJpaEntities(superheros);
	}

	@Override
	public List<Superhero> findByNameContains(String param) {
		var superheros = this.superheroJpaRepository.findByNameContainingIgnoreCase(param.strip());
		return JpaEntityMapper.fromSuperheroJpaEntities(superheros);
	}

	@Override
	public Superhero findById(int id) {
		var superheroJpaEntity = this.superheroJpaRepository.findById(id)
				.orElseThrow(() -> new SuperheroException(ErrorMessage.SUPERHERO_NOT_FOUND_BY_ID, HttpStatus.NOT_FOUND));
		return JpaEntityMapper.fromJpaEntity(superheroJpaEntity);
	}

	@Override
	public Superhero update(Superhero superhero) {
		var superheroJpaEntityUpdated = this.superheroJpaRepository.save(JpaEntityMapper.toJpaEntity(superhero));
		return JpaEntityMapper.fromJpaEntity(superheroJpaEntityUpdated);
	}

	@Override
	public Superhero delete(Superhero superhero) {
		this.superheroJpaRepository.delete(JpaEntityMapper.toJpaEntity(superhero));
		return superhero;
	}

}
