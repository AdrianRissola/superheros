package com.w2m.superheros.application.model.service;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.w2m.superheros.application.model.entities.Superhero;
import com.w2m.superheros.application.model.exceptions.ErrorMessage;
import com.w2m.superheros.application.model.exceptions.SuperheroException;
import com.w2m.superheros.application.ports.in.SuperheroService;
import com.w2m.superheros.application.ports.out.SuperheroRepository;

public class SuperheroServiceImpl implements SuperheroService {
	
	private SuperheroRepository superheroRepository;
	
	@Override
	public List<Superhero> getAll() {
		return this.superheroRepository.findAll();
	}

	@Override
	public List<Superhero> getSuperherosByName(String name) {
		List<Superhero> superheros = this.superheroRepository.findByName(name.strip());
		return superheros;
	}

	@Override
	public Superhero getSuperheroById(int id) {
		Superhero superheroFound = this.superheroRepository.findById(id);
		if(superheroFound==null) 
			throw new SuperheroException(ErrorMessage.SUPERHERO_NOT_FOUND_BY_ID, HttpStatus.NOT_FOUND);
		return superheroFound;
	}

	@Override
	public Superhero update(Superhero superhero) {
		Superhero superheroFound = this.superheroRepository.findById(superhero.getId());
		if(superheroFound==null) 
			throw new SuperheroException(ErrorMessage.SUPERHERO_NOT_FOUND_BY_ID, HttpStatus.NOT_FOUND);
		return this.superheroRepository.update(superhero);
	}

	@Override
	public Superhero remove(int id) {
		Superhero superheroFound = getSuperheroById(id);
		if(superheroFound==null) 
			throw new SuperheroException(ErrorMessage.SUPERHERO_NOT_FOUND_BY_ID, HttpStatus.NOT_FOUND);
		return this.superheroRepository.delete(id);
	}

	@Override
	public Superhero add(Superhero superhero) {
		return null;
	}



}
