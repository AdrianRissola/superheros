package com.w2m.superheros.application.model.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

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
	void testGetSuperherosByName() {
		
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

	private Superhero givenBatman() {
		Superhero batman = new Superhero();
		batman.setHumanBeing(true);
		batman.setName("Batman");
		batman.setRealFullName("Bruce Wayne");
		return batman;
	}

	@Test
	void testGetSuperheroById() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdate() {
		fail("Not yet implemented");
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
