package com.w2m.superheros.adapters.in.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.w2m.superheros.LoadTestingDatabase;
import com.w2m.superheros.application.model.entities.Superhero;

@SpringBootTest
@Import(LoadTestingDatabase.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@ActiveProfiles({ "test" })
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
class SuperheroRestControllerIntegrationTest {
	
	@Autowired
	private MockMvc mockMvc;
	

	@Test
	void getAll_return_200() throws Exception {
				
		this.mockMvc.perform(get("/superheros")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", Is.is("Spiderman")));
	}
	
	@Test
	public void getByName_return_200() throws Exception {
		
		String param = "Man";
		
		this.mockMvc.perform(get("/superheros?name=".concat(param))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", Is.is("Spiderman")));		
	}
	
	@Test
	public void getByName_return_404() throws Exception {
		
		String param = "99";
		
		this.mockMvc.perform(get("/superheros?name=".concat(param))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());		
	}
	
	@Test
	public void getById_return_200() throws Exception {
		
		this.mockMvc.perform(get("/superheros/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", Is.is("Spiderman")));		
	}
	
	@Test
	public void getById_return_404() throws Exception {
		
		this.mockMvc.perform(get("/superheros/{id}", 9999)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
	}
	
	@Test
	public void update_return_200() throws Exception {
				
		this.mockMvc.perform(put("/superheros/{id}", 1)
				.content(new ObjectMapper().writeValueAsString(this.givenSpiderman()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", Is.is(this.givenSpiderman().getName())));		
	}
	
	@Test
	public void update_return_404() throws Exception {
				
		this.mockMvc.perform(put("/superheros/{id}", 2)
				.content(new ObjectMapper().writeValueAsString(this.givenSpiderman()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());		
	}
	
	@Test
	public void update_return_400() throws Exception {
				
		Superhero invalidSpiderman = this.givenSpiderman();
		invalidSpiderman.setName(null);
		this.mockMvc.perform(put("/superheros/{id}", 2)
				.content(new ObjectMapper().writeValueAsString(invalidSpiderman))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());		
	}
	
	
	@Test
	public void remove_return_200() throws Exception {
		
		this.mockMvc.perform(get("/superheros/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
	}
	
	@Test
	public void remove_return_404() throws Exception {
		
		this.mockMvc.perform(get("/superheros/{id}", 99)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
	}
	
	
	private Superhero givenSpiderman() {
		Superhero superman = new Superhero();
		superman.setId(1);
		superman.setHumanBeing(false);
		superman.setName("Spider-man");
		superman.setRealFullName("Spider-man");
		return superman;
	}
	


}
