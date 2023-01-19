package com.w2m.superheros.application.model.entities;

public class Power {

	private int id;
	
	private String name;

	private int intensity;

	private boolean isEnabled;

    private Superhero superhero;
	
	public Power() {
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

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public Superhero getSuperhero() {
		return superhero;
	}

	public void setSuperhero(Superhero superhero) {
		this.superhero = superhero;
	}

	public int getIntensity() {
		return intensity;
	}

	public void setIntensity(int intensity) {
		this.intensity = intensity;
	}

}
