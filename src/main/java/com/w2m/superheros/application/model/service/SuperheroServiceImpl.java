package com.w2m.superheros.application.model.service;

import java.util.List;

import com.w2m.superheros.application.model.entities.Superhero;
import com.w2m.superheros.application.ports.in.SuperheroService;
import com.w2m.superheros.application.ports.out.SuperheroRepository;

public class SuperheroServiceImpl implements SuperheroService {
	
	private SuperheroRepository superheroRepository;

	@Override
	public List<Superhero> getSuperherosByName(String name) {
		List<Superhero> superheros = this.superheroRepository.findByName(name.strip());
		return superheros;
	}

	@Override
	public Superhero getSuperheroById(int id) {
		return this.superheroRepository.findById(id);
	}

	@Override
	public Superhero update(Superhero superhero) {
		return this.superheroRepository.update(superhero);
	}

	@Override
	public Superhero remove(int id) {
		return null;
	}

	@Override
	public Superhero add(Superhero superhero) {
		return null;
	}

}
