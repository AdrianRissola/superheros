package com.w2m.superheros.application.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.w2m.superheros.application.model.entities.Superhero;
import com.w2m.superheros.application.model.exceptions.ErrorMessage;
import com.w2m.superheros.application.model.exceptions.SuperheroException;
import com.w2m.superheros.application.ports.in.SuperheroService;
import com.w2m.superheros.application.ports.out.SuperheroRepository;
import com.w2m.superheros.helper.Utils;

@Service("superheroService")
public class SuperheroServiceImpl implements SuperheroService {
	
	@Autowired
	private SuperheroRepository superheroRepository;
	
	@Cacheable(value = "superheros")
	@Override
	public List<Superhero> getAll() {
		return this.superheroRepository.findAll();
	}

	@Cacheable(value = "superheros")
	@Override
	public List<Superhero> getByNameContains(String name) {
		var superheros = this.superheroRepository.findByNameContains(name.strip());
		Utils.validateIsNullOrEmpty(superheros,
				new SuperheroException(ErrorMessage.SUPERHEROS_NOT_FOUND_BY_NAME, HttpStatus.NOT_FOUND));
		return superheros;
	}

	@Override
	public Superhero getById(int id) {
		var superheroFound = this.superheroRepository.findById(id);
		Utils.validateIsNull(superheroFound,
				new SuperheroException(ErrorMessage.SUPERHERO_NOT_FOUND_BY_ID, HttpStatus.NOT_FOUND));
		return superheroFound;
	}

	@CacheEvict(value="superheros", allEntries=true)
	@Override
	public Superhero update(Superhero superhero) {
		var superheroFound = this.superheroRepository.findById(superhero.getId());
		Utils.validateIsNull(superheroFound,
				new SuperheroException(ErrorMessage.SUPERHERO_NOT_FOUND_BY_ID, HttpStatus.NOT_FOUND));
		return this.superheroRepository.update(superhero);
	}

	@CacheEvict(value="superheros", allEntries=true)
	@Override
	public Superhero removeById(int id) {
		var superheroFound = getById(id);
		Utils.validateIsNull(superheroFound,
				new SuperheroException(ErrorMessage.SUPERHERO_NOT_FOUND_BY_ID, HttpStatus.NOT_FOUND));
		return this.superheroRepository.delete(superheroFound);
	}



}
