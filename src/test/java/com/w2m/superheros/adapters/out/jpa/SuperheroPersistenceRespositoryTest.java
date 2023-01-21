package com.w2m.superheros.adapters.out.jpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.w2m.superheros.adapters.out.jpa.entities.SuperheroJpaEntity;
import com.w2m.superheros.application.model.entities.Superhero;

class SuperheroPersistenceRespositoryTest {

	@Mock
	private SuperheroJpaRepository superheroJpaRepositoryMock;
	
	@InjectMocks
	private SuperheroPersistenceRespository superheroPersistenceRespository;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void findAll() {
		
		// given
		when(this.superheroJpaRepositoryMock.findAll()).thenReturn(this.givenSuperherosJpaEntities());
		
		// when
		List<Superhero> superheros = this.superheroPersistenceRespository.findAll();
		
		// expect
		assertNotNull(superheros);
		assertEquals(superheros.size(), 2);
	
	}
	
	@Test
	void findByNameContains() {
		
		// given
		String param = "Man";
		when(this.superheroJpaRepositoryMock.findByNameContainingIgnoreCase(param)).thenReturn(this.givenSuperherosJpaEntities());
		
		// when
		List<Superhero> superheros = this.superheroPersistenceRespository.findByNameContains(param);
		
		// expect
		assertNotNull(superheros);
		assertEquals(superheros.size(), 2);
	
	}
	
	@Test
	void findById() {
		
		// given
		int id = 1;
		when(this.superheroJpaRepositoryMock.findById(id)).thenReturn(Optional.of(this.givenSupermanJpaEntity()));
		
		// when
		Superhero superhero = this.superheroPersistenceRespository.findById(id);
		
		// expect
		assertNotNull(superhero);
	
	}
	
	@Test
	void update() {
		
		// given
		when(this.superheroJpaRepositoryMock.save(any(SuperheroJpaEntity.class))).thenReturn(this.givenBatmanJpaEntity());
		
		// when
		Superhero superhero = this.superheroPersistenceRespository.update(this.givenBatman());
		
		// expect
		assertNotNull(superhero);
	
	}
	
	@Test
	void delete() {
		
		// given
		doNothing().when(this.superheroJpaRepositoryMock).delete(any(SuperheroJpaEntity.class));
		
		// when
		Superhero superhero = this.superheroPersistenceRespository.delete(this.givenBatman());
		
		// expect
		assertNotNull(superhero);
	
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
	
	private Superhero givenBatman() {
		Superhero batman = new Superhero();
		batman.setId(1);
		batman.setHumanBeing(true);
		batman.setName("Batman");
		batman.setRealFullName("Bruce Wayne");
		return batman;
	}
	
}
