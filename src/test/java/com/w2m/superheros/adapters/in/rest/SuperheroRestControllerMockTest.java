package com.w2m.superheros.adapters.in.rest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.w2m.superheros.DummyFactory;
import com.w2m.superheros.application.model.entities.Superhero;
import com.w2m.superheros.application.model.exceptions.SuperheroException;
import com.w2m.superheros.application.ports.in.SuperheroService;

@SpringBootTest
@AutoConfigureMockMvc
class SuperheroRestControllerMockTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private SuperheroService superheroService;

	@Test
	void getAll_return_200() throws Exception {
		
		when(this.superheroService.getAll()).thenReturn(this.givenSuperheros());
		
		this.mockMvc.perform(get("/superheros")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", Is.is(this.givenSuperheros().get(0).getName())))
                .andExpect(jsonPath("$[1].name", Is.is(this.givenSuperheros().get(1).getName())));
	}
	
	@Test
	public void getByName_return_200() throws Exception {
		
		String param = "Man";
		when(this.superheroService.getByNameContains(param)).thenReturn(givenSuperheros());
		
		this.mockMvc.perform(get("/superheros?name=".concat(param))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", Is.is(givenSuperheros().get(0).getName())));		
	}
	
	@Test
	public void getById_return_200() throws Exception {
		
		when(this.superheroService.getById(this.givenSuperman().getId())).thenReturn(this.givenSuperman());
		
		this.mockMvc.perform(get("/superheros/{id}", this.givenSuperman().getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", Is.is(this.givenSuperman().getName())));		
	}
	
	@Test
	public void getById_return_404() throws Exception {
		
		when(this.superheroService.getById(any(Integer.class)))
		.thenThrow(new SuperheroException(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND));
		
		this.mockMvc.perform(get("/superheros/{id}", 9999)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
	}
	
	@Test
	public void update_return_200() throws Exception {
		
		when(this.superheroService.update(any(Superhero.class))).thenReturn(givenSuperman());
		
		this.mockMvc.perform(put("/superheros/{id}", givenSuperman().getId())
				.content(new ObjectMapper().writeValueAsString(this.givenSuperman()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", Is.is(this.givenSuperman().getName())));		
	}
	
	private Superhero givenSuperman() {
		return DummyFactory.getSuperman(1);
	}
	
	private Superhero givenBatman() {
		return DummyFactory.getBatman(2);
	}
	
	private List<Superhero> givenSuperheros() {
		return Arrays.asList(givenBatman(), this.givenSuperman());
	}

}
