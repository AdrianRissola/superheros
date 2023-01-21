package com.w2m.superheros.adapters.out.jpa;

import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.w2m.superheros.adapters.out.jpa.entities.SuperheroJpaEntity;
import com.w2m.superheros.application.model.entities.Superhero;

class SuperheroPersistenceRespositoryTest {

	@Mock
	private SuperheroJpaRepository superheroJpaRepository;
	
	@InjectMocks
	private SuperheroPersistenceRespository superheroPersistenceRespository;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void findAll() {
		
		// given
		when(this.superheroJpaRepository.findAll()).thenReturn(this.givenSuperherosJpaEntities());
		
		// when
		List<Superhero> superheros = this.superheroPersistenceRespository.findAll();
		
		// expect
		assertNotNull(superheros);
		assertEquals(superheros.size(), 2);
	
	}
	
	
	private SuperheroJpaEntity givenSupermanJpaEntity() {
		SuperheroJpaEntity superman = new SuperheroJpaEntity();
		superman.setId(1);
		superman.setHumanBeing(false);
		superman.setName("Superman");
		superman.setRealFullName("Clark Kent");
		return superman;
	}
	
	private SuperheroJpaEntity givenBatmanJpaEntity() {
		SuperheroJpaEntity batman = new SuperheroJpaEntity();
		batman.setHumanBeing(true);
		batman.setName("Batman");
		batman.setRealFullName("Bruce Wayne");
		return batman;
	}
	
	private List<SuperheroJpaEntity> givenSuperherosJpaEntities() {
		return Arrays.asList(givenSupermanJpaEntity(), this.givenBatmanJpaEntity());
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
