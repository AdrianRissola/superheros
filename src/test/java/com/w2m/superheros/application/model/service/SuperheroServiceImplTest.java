package com.w2m.superheros.application.model.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.w2m.superheros.application.model.entities.Superhero;
import com.w2m.superheros.application.ports.in.SuperheroService;
import com.w2m.superheros.application.ports.out.SuperheroRepository;

class SuperheroServiceImplTest {
	
	@Mock
    private SuperheroRepository superheroRepository;
    
	@InjectMocks
	private SuperheroService superheroService =  new SuperheroServiceImpl();
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testGetSuperherosByName_successful() {
		
		// given
		Superhero batman = this.givenBatman();
		when(this.superheroRepository.findByName(batman.getName())).thenReturn(Arrays.asList(batman));
		
		// when
		List<Superhero> superhero = superheroService.getSuperherosByName("Batman");
		
		// then
		MatcherAssert.assertThat(
				superhero, 
				containsInAnyOrder(hasProperty("name", is(batman.getName()))));
	}
	
	@Test
	void testGetSuperherosByNameReturnMultipleSuperheros_successful() {
		
		// given
		Superhero batman = this.givenBatman();
		Superhero superman = this.givenSuperman();
		when(this.superheroRepository.findByName("man")).thenReturn(Arrays.asList(batman, superman));
		
		// when
		List<Superhero> superheros = superheroService.getSuperherosByName("man");
		
		// then
		MatcherAssert.assertThat(superheros, containsInAnyOrder(batman, superman));
	}
	
	
	private Superhero givenSuperman() {
		Superhero superman = new Superhero();
		superman.setId(1);
		superman.setHumanBeing(false);
		superman.setName("Superman");
		superman.setRealFullName("Clark Kent");
		return superman;
	}

	@Test
	public void getSuperherosByEmptyName_return_empty_list() {
		
		String name = "";
		when(this.superheroRepository.findByName(name)).thenReturn(new ArrayList<>());
		
		List<Superhero> superheros = this.superheroService.getSuperherosByName(name);
		
		assertThat(superheros).isEmpty();
	}

	private Superhero givenBatman() {
		Superhero batman = new Superhero();
		batman.setHumanBeing(true);
		batman.setName("Batman");
		batman.setRealFullName("Bruce Wayne");
		return batman;
	}

	@Test
	void testGetSuperheroById() {
		when(superheroRepository.findById(givenSuperman().getId())).thenReturn(givenSuperman());
		
		Superhero superheros = superheroService.getSuperheroById(1);
		
		assertThat(superheros.getName()).isEqualTo(givenSuperman().getName());
	}

	@Test
	void testUpdate() {
		when(superheroRepository.update(givenSuperman())).thenReturn(givenSuperman());
				
		Superhero superman = superheroService.update(givenSuperman());
		assertNotNull(superman);
	}

	@Test
	void testRemove() {
		fail("Not yet implemented");
	}

	@Test
	void testAdd() {
		fail("Not yet implemented");
	}

}
