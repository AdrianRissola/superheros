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
import com.w2m.superheros.timetraceable.TimeTraceable;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class SuperheroRestController {

	@Autowired
	private SuperheroService superheroService;
	
	/**
     * @param name: superhero name
     * @return List<Superhero>
     */
    @ApiOperation(value = "Get superheros", notes = "This method find superheros")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
	@GetMapping("/superheros")
	@ResponseStatus(HttpStatus.OK)
	@TimeTraceable
	public List<Superhero> getSuperheros(
			@ApiParam(
					name =  "name",
					type = "String",
    			    value = "superhero name",
    			    example = "superman",
    			    required = false)
			@RequestParam(required = false) String name){
		return (name==null) ? this.superheroService.getAll() : this.superheroService.getByNameContains(name);
	}
	
	/**
     * @param id: superhero id
     * @return Superhero
     */
    @ApiOperation(value = "Get superhero by id", notes = "This method finds a superhero by id")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "superhero not found by requested id"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
	@GetMapping("/superheros/{id}")
	@ResponseStatus(HttpStatus.OK)
	@TimeTraceable
	public Superhero getSuperheroById(
			@ApiParam(
					name =  "id",
					type = "int",
    			    value = "superhero id",
    			    example = "1",
    			    required = true)
			@PathVariable int id){
		return this.superheroService.getById(id);
	}
	
	/**
     * @param id: superhero id
     * @param superhero: superhero
     * @return Superhero
     */
    @ApiOperation(value = "Update superhero by id", notes = "This method updates a superhero by id")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "superhero not found by requested id"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
	@PutMapping("/superheros/{id}")
	@ResponseStatus(HttpStatus.OK)
	@TimeTraceable
	public Superhero update(
			@ApiParam(
					name =  "id",
					type = "int",
    			    value = "superhero id",
    			    example = "1",
    			    required = true)
			@PathVariable int id,
			@ApiParam(
					name =  "superhero",
					type = "SuperheroDto",
    			    value = "superhero",
    			    required = true)
			@Valid @RequestBody Superhero superhero){
		superhero.setId(id);
		return this.superheroService.update(superhero);
	}
	
	/**
     * @param id: superhero id
     * @return Superhero
     */
    @ApiOperation(value = "Remove superhero by id", notes = "This method removes a superhero by id")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "superhero not found by requested id"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
	@DeleteMapping("/superheros/{id}")
	@ResponseStatus(HttpStatus.OK)
	@TimeTraceable
	public Superhero removeSuperhero(
			@ApiParam(
					name =  "id",
					type = "int",
    			    value = "superhero id",
    			    example = "1",
    			    required = true)
			@PathVariable int id){
		return this.superheroService.removeById(id);
	}
}
