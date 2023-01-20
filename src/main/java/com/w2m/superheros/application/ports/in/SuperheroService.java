package com.w2m.superheros.application.ports.in;

import java.util.List;

import com.w2m.superheros.application.model.entities.Superhero;

public interface SuperheroService {

	List<Superhero> getAll();
	
	List<Superhero> getSuperherosByName(String name);

	Superhero getSuperheroById(int id);

	Superhero update(Superhero superhero);

	Superhero remove(int id);

	Superhero add(Superhero superhero);

}
