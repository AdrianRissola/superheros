package com.w2m.superheros.application.model.entities;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.http.HttpStatus;

import com.w2m.superheros.application.model.exceptions.SuperheroException;
import com.w2m.superheros.helper.Utils;


public class Superhero {

	private int id;
	
	@NotBlank(message = "'name' is required for a superhero")
	private String name;
	
	@NotBlank(message = "'realFullName' is required for a superhero")
	private String realFullName;
	
	@NotNull(message = "'humanBeing' boolean field is required, allowed input: 'true' or f'alse'")
	private Boolean isHumanBeing;
	
	@NotNull(message = "'powers' list is required for a superhero")
	@NotEmpty(message = "'powers' list cannot be empty.")
	@Size(min = 1, message = "a superhero shoud has at least uno superpower")
	private List<
		@Valid 
		@NotNull(message = "'power' should not be null")
		Power> powers;
	
	
	private Superhero(int id, String name, String realFullName, boolean isHumanBeing, List<Power> powers){
		this.id = id;
		this.name = name;
		this.realFullName = realFullName;
		this.isHumanBeing = isHumanBeing;
		this.powers = new ArrayList<>(powers);
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public List<Power> getPowers() {
		return new ArrayList<>(this.powers);
	}

	public String getRealFullName() {
		return realFullName;
	}

	public boolean isHumanBeing() {
		return isHumanBeing;
	}
	
	
	public static class Builder {
		
		private int id;
		
		private String name;
		
		private String realFullName;
		
		private boolean isHumanBeing;
		
		private List<Power> powers;
		
		public Builder(String name, String realFullName, boolean isHumanBeing, List<Power> powers) {
			Utils.validateNullOrBlank(
					new SuperheroException("name and realFullName are mandatory", null), name, realFullName);
			Utils.validateIsNullOrEmpty(powers, new SuperheroException(
					"a superhero needs at least one superpower", HttpStatus.BAD_REQUEST));
			this.name = name;
			this.realFullName = realFullName;
			this.isHumanBeing = isHumanBeing;
			this.powers = powers;
		}
		
		public Builder withId(int id) {
			this.id = id;
			return this;
		}
		
		public Superhero build() {
			return new Superhero(this.id, this.name, this.realFullName, this.isHumanBeing, this.powers);
		}
	}


}
