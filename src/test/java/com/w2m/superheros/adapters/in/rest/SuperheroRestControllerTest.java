package com.w2m.superheros.adapters.in.rest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.w2m.superheros.application.model.entities.Superhero;
import com.w2m.superheros.application.model.service.SuperheroServiceImpl;
import com.w2m.superheros.application.ports.in.SuperheroService;


class SuperheroRestControllerTest {
	
	private MockMvc mockMvc;
	
	@Mock
	private SuperheroService superheroService =  new SuperheroServiceImpl();
	
	@Autowired
	private WebApplicationContext webApplicationContext;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
	}

	@Test
	void getAll() throws Exception {
		
		when(this.superheroService.getAll()).thenReturn(this.givenSuperheros());
		
		this.mockMvc.perform(get("/superheros")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", Is.is(this.givenSuperheros().get(0).getName())))
                .andExpect(jsonPath("$[1].name", Is.is(this.givenSuperheros().get(1).getName())));
	}
	
	
	private Superhero givenSuperman() {
		Superhero superman = new Superhero();
		superman.setId(1);
		superman.setHumanBeing(false);
		superman.setName("Superman");
		superman.setRealFullName("Clark Kent");
		return superman;
	}
	
	private Superhero givenBatman() {
		Superhero batman = new Superhero();
		batman.setHumanBeing(true);
		batman.setName("Batman");
		batman.setRealFullName("Bruce Wayne");
		return batman;
	}
	
	private List<Superhero> givenSuperheros() {
		return Arrays.asList(givenBatman(), this.givenSuperman());
	}

}
