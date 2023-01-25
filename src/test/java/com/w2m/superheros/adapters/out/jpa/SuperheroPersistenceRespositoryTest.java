package com.w2m.superheros.adapters.out.jpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.w2m.superheros.DummyFactory;
import com.w2m.superheros.adapters.out.jpa.entities.PowerJpaEntity;
import com.w2m.superheros.adapters.out.jpa.entities.SuperheroJpaEntity;
import com.w2m.superheros.application.model.entities.Power;
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
		List<PowerJpaEntity> powers = new ArrayList<>();
		PowerJpaEntity laser = new PowerJpaEntity();
		laser.setName("laser");
		laser.setIntensity(5);
		laser.setEnabled(true);
		powers.add(laser);
		SuperheroJpaEntity superman = new SuperheroJpaEntity();
		superman.setId(1);
		superman.setHumanBeing(false);
		superman.setName("Superman");
		superman.setRealFullName("Clark Kent");
		superman.setPowers(powers);
		return superman;
	}
	
	private SuperheroJpaEntity givenBatmanJpaEntity() {
		List<PowerJpaEntity> powers = new ArrayList<>();
		PowerJpaEntity armor = new PowerJpaEntity();
		armor.setName("armor");
		armor.setIntensity(5);
		armor.setEnabled(true);
		powers.add(armor);
		SuperheroJpaEntity batman = new SuperheroJpaEntity();
		batman.setHumanBeing(true);
		batman.setName("Batman");
		batman.setRealFullName("Bruce Wayne");
		batman.setPowers(powers);
		return batman;
	}
	
	private List<SuperheroJpaEntity> givenSuperherosJpaEntities() {
		return Arrays.asList(givenSupermanJpaEntity(), this.givenBatmanJpaEntity());
	}
	
	private Superhero givenBatman() {
		return DummyFactory.getBatman(2);
	}
	
}
