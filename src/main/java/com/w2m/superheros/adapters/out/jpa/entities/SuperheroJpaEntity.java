package com.w2m.superheros.adapters.out.jpa.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class SuperheroJpaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private String realFullName;
	
	private boolean isHumanBeing;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "SUPERHERO_JPA_ENTITY_ID")
	private List<PowerJpaEntity> powers = new ArrayList<PowerJpaEntity>();
	
	public SuperheroJpaEntity(){
	}
	
	public SuperheroJpaEntity(String name, String realFullName, boolean isHumanBeing){
		this.setName(name);
		this.setRealFullName(realFullName);
		this.setHumanBeing(isHumanBeing);
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
