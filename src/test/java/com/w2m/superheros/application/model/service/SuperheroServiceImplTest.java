package com.w2m.superheros.application.model.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
import org.springframework.http.HttpStatus;

import com.w2m.superheros.application.model.entities.Superhero;
import com.w2m.superheros.application.model.exceptions.SuperheroException;
import com.w2m.superheros.application.ports.in.SuperheroService;
import com.w2m.superheros.application.ports.out.SuperheroRepository;

class SuperheroServiceImplTest {
	
	@Mock
    private SuperheroRepository superheroRepositoryMock;
    
	@InjectMocks
	private SuperheroService superheroService =  new SuperheroServiceImpl();
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void getAll() {
		
		// given
		List<Superhero> givenSuperheros = this.givenSuperheros();
		when(this.superheroRepositoryMock.findAll()).thenReturn(givenSuperheros);
		
		// when
		List<Superhero> superheros = this.superheroService.getAll();
		
		// then
		MatcherAssert.assertThat(
				superheros, 
				containsInAnyOrder(givenSuperheros.get(0), givenSuperheros.get(1)));
		
	}
	
	@Test
	public void findAll_return_empty_list() {
		
		when(superheroRepositoryMock.findAll()).thenReturn(new ArrayList<>());
		
		List<Superhero> superheros = superheroService.getAll();
		
		assertThat(superheros).isEmpty();
	}

	@Test
	void testGetSuperherosByName_successful() {
		
		// given
		Superhero batman = this.givenBatman();
		when(this.superheroRepositoryMock.findByName(batman.getName())).thenReturn(Arrays.asList(batman));
		
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
		List<Superhero> givenSuperheros = this.givenSuperheros();
		when(this.superheroRepositoryMock.findByName("man")).thenReturn(givenSuperheros);
		
		// when
		List<Superhero> superheros = this.superheroService.getSuperherosByName("man");
		
		// then
		MatcherAssert.assertThat(superheros, hasSize(2));
		MatcherAssert.assertThat(superheros, 
				containsInAnyOrder(givenSuperheros.get(0), givenSuperheros.get(1)));
		
	}
	
	
	private Superhero givenSuperman() {
		Superhero superman = new Superhero();
		superman.setId(1);
		superman.setHumanBeing(false);
		superman.setName("Superman");
		superman.setRealFullName("Clark Kent");
		return superman;
	}
	
	private List<Superhero> givenSuperheros() {
		return Arrays.asList(givenBatman(), this.givenSuperman());
	}

	@Test
	public void getSuperherosByEmptyName_return_empty_list() {
		// given
		String name = "";
		when(this.superheroRepositoryMock.findByName(name)).thenReturn(new ArrayList<>());
		
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
		
		// given
		when(this.superheroRepositoryMock.findById(this.givenSuperman().getId())).thenReturn(this.givenSuperman());
		
		// when
		Superhero superheros = this.superheroService.getSuperheroById(1);
		
		//expect
		assertThat(superheros.getName()).isEqualTo(this.givenSuperman().getName());
	}
	
	@Test
	void testGetSuperheroByNonExistingId_throw_exception() {
		
		// given
		when(this.superheroRepositoryMock.findById(this.givenSuperman().getId())).thenReturn(null);
			
		// when
        Exception exception = assertThrows(RuntimeException.class, () -> {
        	this.superheroService.getSuperheroById(1);
        });
        
        //expect
        String expectedMessage = "superhero not found by requested id";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	public void updateSuperheroByNonExistingId_throw_exception() {

		// given
		int nonExistingId = 999;
		when(this.superheroRepositoryMock.findById(nonExistingId))
		.thenReturn(null);
						
		// when
        Exception exception = assertThrows(RuntimeException.class, () -> {
        	this.superheroService.update(givenSuperman());
        });
        
        //expect
        String expectedMessage = "superhero not found by requested id";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

	}

	@Test
	void testUpdate_successful() {
		
		// given
		Superhero superman = this.givenSuperman();
		when(this.superheroRepositoryMock.findById(superman.getId())).thenReturn(superman);
		when(this.superheroRepositoryMock.update(superman)).thenReturn(superman);
			
		// when
		Superhero supermanUpdated = this.superheroService.update(superman);
		
		//expect
		assertNotNull(supermanUpdated);
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
