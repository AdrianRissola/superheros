package com.w2m.superheros.application.ports.out;

import java.util.List;

import com.w2m.superheros.application.model.entities.Superhero;

public interface SuperheroRepository {
	
	
	List<Superhero> findByName(String name);

	Superhero findById(int id);

	Superhero update(Superhero superhero);

}
