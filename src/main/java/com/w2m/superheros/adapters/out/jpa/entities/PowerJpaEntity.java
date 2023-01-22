package com.w2m.superheros.adapters.out.jpa.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class PowerJpaEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private int intensity;
	
	private boolean isEnabled;

	@ManyToOne
    private SuperheroJpaEntity superhero;
    
	public PowerJpaEntity() {
	}

	public PowerJpaEntity(String name, int intensity, boolean isEnabled) {
		this.setName(name);
		this.setIntensity(intensity);
		this.setEnabled(isEnabled);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIntensity() {
		return intensity;
	}

	public void setIntensity(int intensity) {
		this.intensity = intensity;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public SuperheroJpaEntity getSuperhero() {
		return superhero;
	}

	public void setSuperhero(SuperheroJpaEntity superhero) {
		this.superhero = superhero;
	}

}
