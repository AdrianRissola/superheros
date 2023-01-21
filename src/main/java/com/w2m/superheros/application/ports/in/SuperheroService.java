package com.w2m.superheros.application.ports.in;

import java.util.List;

import com.w2m.superheros.application.model.entities.Superhero;

public interface SuperheroService {

	List<Superhero> getAll();
	
	List<Superhero> getByNameContains(String param);

	Superhero getById(int id);

	Superhero update(Superhero superhero);

	Superhero removeById(int id);

}
