package com.w2m.superheros.application.model.entities;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class Superhero {

	private int id;
	
	@NotBlank(message = "name is required")
	private String name;
	
	@NotBlank(message = "realFullName is required")
	private String realFullName;
	
	@NotNull(message = "'humanBeing' boolean field is required, allowed input: 'true' or f'alse'")
	private boolean isHumanBeing;
	
	private List<Power> powers = new ArrayList<Power>();
	
	public Superhero(){}	

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

	public List<Power> getPowers() {
		return powers;
	}

	public void setPowers(List<Power> powers) {
		this.powers = powers;
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


}
