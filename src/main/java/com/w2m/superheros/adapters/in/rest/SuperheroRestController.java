package com.w2m.superheros.adapters.in.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.w2m.superheros.application.model.entities.Superhero;
import com.w2m.superheros.application.ports.in.SuperheroService;

@RestController
public class SuperheroRestController {

	@Autowired
	private SuperheroService superheroService;
	
	/**
     * @param name: superhero name
     * @return List<Superhero>
     */
	@GetMapping("/superheros")
	@ResponseStatus(HttpStatus.OK)
	public List<Superhero> getSuperherosByName(@RequestParam(required = false) String name){
		return (name==null) ? this.superheroService.getAll() : this.superheroService.getByNameContains(name);
	}
	
	/**
     * @param id: superhero id
     * @return Superhero
     */
	@GetMapping("/superheros/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Superhero getSuperheroById(@PathVariable int id){
		return this.superheroService.getById(id);
	}
	
	/**
     * @param id: superhero id
     * @param superhero: superhero
     * @return Superhero
     */
	@PutMapping("/superheros/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Superhero update(@PathVariable int id, @Valid @RequestBody Superhero superhero){
		superhero.setId(id);
		return this.superheroService.update(superhero);
	}
	
	/**
     * @param id: superhero id
     * @return Superhero
     */
	@DeleteMapping("/superheros/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Superhero removeSuperhero(@PathVariable int id){
		return this.superheroService.removeById(id);
	}
}
