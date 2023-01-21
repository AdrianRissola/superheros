package com.w2m.superheros.adapters.in.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.w2m.superheros.application.model.entities.Superhero;
import com.w2m.superheros.application.ports.in.SuperheroService;

@RestController
public class SuperheroRestController {

	private SuperheroService superheroService;
	
	/**
     * @param name: superhero name
     * @return List<SuperheroDto>
     */
	@GetMapping("/superheros")
	@ResponseStatus(HttpStatus.OK)
	public List<Superhero> getAll() {
		return this.superheroService.getAll();
	}
}
