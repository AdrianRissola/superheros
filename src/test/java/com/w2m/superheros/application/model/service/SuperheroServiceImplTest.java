package com.w2m.superheros.application.model.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
	public void getAll_return_empty_list() {
		
		when(superheroRepositoryMock.findAll()).thenReturn(new ArrayList<>());
		
		List<Superhero> superheros = superheroService.getAll();
		
		assertThat(superheros).isEmpty();
	}

	
	@Test
	void getSuperherosByName_successful() {
		
		// given
		Superhero batman = this.givenBatman();
		when(this.superheroRepositoryMock.findByNameContains(batman.getName())).thenReturn(Arrays.asList(batman));
		
		// when
		List<Superhero> superhero = superheroService.getByNameContains("Batman");
		
		// then
		MatcherAssert.assertThat(
				superhero, 
				containsInAnyOrder(hasProperty("name", is(batman.getName()))));
	}
	
	
	@Test
	void getSuperherosByNameReturnMultipleSuperheros_successful() {
		
		// given
		List<Superhero> givenSuperheros = this.givenSuperheros();
		when(this.superheroRepositoryMock.findByNameContains("man")).thenReturn(givenSuperheros);
		
		// when
		List<Superhero> superheros = this.superheroService.getByNameContains("man");
		
		// then
		MatcherAssert.assertThat(superheros, hasSize(2));
		MatcherAssert.assertThat(superheros, 
				containsInAnyOrder(givenSuperheros.get(0), givenSuperheros.get(1)));
		
	}

	
	@Test
	public void getSuperherosByEmptyName_return_empty_list() {
		// given
		String name = "";
		when(this.superheroRepositoryMock.findByNameContains(name)).thenReturn(new ArrayList<>());
		
		List<Superhero> superheros = this.superheroService.getByNameContains(name);
		
		assertThat(superheros).isEmpty();
	}


	@Test
	void getSuperheroById() {
		
		// given
		when(this.superheroRepositoryMock.findById(this.givenSuperman().getId())).thenReturn(this.givenSuperman());
		
		// when
		Superhero superheros = this.superheroService.getById(1);
		
		//expect
		assertThat(superheros.getName()).isEqualTo(this.givenSuperman().getName());
	}
	
	@Test
	void getSuperheroByNonExistingId_throw_exception() {
		
		// given
		when(this.superheroRepositoryMock.findById(this.givenSuperman().getId())).thenReturn(null);
			
		// when
        Exception exception = assertThrows(RuntimeException.class, () -> {
        	this.superheroService.getById(1);
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
	void update_successful() {
		
		// given
		when(this.superheroRepositoryMock.findById(1)).thenReturn(this.givenSuperman());
		
		Superhero supermanRequest = this.givenSuperman();
		supermanRequest.setId(1);
		supermanRequest.setRealFullName("Juan Perez");
		when(this.superheroRepositoryMock.update(supermanRequest)).thenReturn(supermanRequest);
			
		// when
		Superhero supermanUpdated = this.superheroService.update(supermanRequest);
		
		// expect
		assertNotNull(supermanUpdated);
		assertEquals(supermanRequest.getRealFullName(), supermanUpdated.getRealFullName());
	}

	@Test
	void remove_successful() {
		
		// given
		Superhero givenSuperman = this.givenSuperman();
		when(this.superheroRepositoryMock.findById(this.givenSuperman().getId())).thenReturn(givenSuperman);
		when(this.superheroRepositoryMock.delete(givenSuperman)).thenReturn(this.givenSuperman());
		
		// when
		Superhero superhero = this.superheroService.removeById(givenSuperman.getId());
		
		// expect
		assertNotNull(superhero);
	}
	
	@Test
	void remove_non_existing_superheroById_throws_exception() {
		
		// given
		when(this.superheroRepositoryMock.findById(givenSuperman().getId())).thenReturn(null);		
		
		// when
        Exception exception = assertThrows(RuntimeException.class, () -> {
        	this.superheroService.removeById(givenSuperman().getId());
        });
        
        //expect
        String expectedMessage = "superhero not found by requested id";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
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
