package com.w2m.superheros.adapters.out.jpa.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class SuperheroJpaEntity {

	@Id
	private int id;
	
	private String name;
	
	private String realFullName;
	
	private boolean isHumanBeing;
	
	@OneToMany
	private List<PowerJpaEntity> powers = new ArrayList<PowerJpaEntity>();
	

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

	public String getRealFullName() {
		return realFullName;
	}

	public void setRealFullName(String realFullName) {
		this.realFullName = realFullName;
	}

	public boolean isHumanBeing() {
		return isHumanBeing;
	}

	public void setHumanBeing(boolean isHumanBeing) {
		this.isHumanBeing = isHumanBeing;
	}

	public List<PowerJpaEntity> getPowers() {
		return powers;
	}

	public void setPowers(List<PowerJpaEntity> powers) {
		this.powers = powers;
	}
	
}
