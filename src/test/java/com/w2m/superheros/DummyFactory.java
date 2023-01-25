package com.w2m.superheros;

import java.util.ArrayList;
import java.util.List;

import com.w2m.superheros.application.model.entities.Power;
import com.w2m.superheros.application.model.entities.Superhero;

public class DummyFactory {
	
	public static Superhero getSuperman(int id) {
		Power power = new Power.Builder("laser", 10, true).build();
		List<Power> powers = new ArrayList<>();
		powers.add(power);
		return new Superhero.Builder("Superman", "Clark Kent", false, powers).withId(id).build();
	}
	
	public static Superhero getBatman(int id) {
		Power power = new Power.Builder("armor", 10, true).build();
		List<Power> powers = new ArrayList<>();
		powers.add(power);
		return new Superhero.Builder("Batman", "Bruce Wayne", true, powers).withId(id).build();
	}
	
	public static Superhero getSpiderman() {
		Power power = new Power.Builder("net", 10, true).build();
		List<Power> powers = new ArrayList<>();
		powers.add(power);
		return new Superhero.Builder("Spider-man", "Spider-man", false, powers).build();
	}
}
