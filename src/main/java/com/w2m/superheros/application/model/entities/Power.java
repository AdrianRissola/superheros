package com.w2m.superheros.application.model.entities;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.w2m.superheros.application.model.exceptions.SuperheroException;
import com.w2m.superheros.helper.Utils;

public class Power {

	private int id;
	
	@NotBlank(message = "'name' is required")
	private String name;

	private int intensity;

	@NotNull(message = "'isEnabled' boolean field is required, allowed input: 'true' or f'alse'")
	private Boolean isEnabled;

	
	private Power(int id, String name, int intensity, boolean isEnabled) {
		this.id = id;
		this.name = name;
		this.intensity = intensity;
		this.setEnabled(isEnabled);
	}
	
	private void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public boolean isEnabled() {
		return isEnabled;
	}
	
	public int getIntensity() {
		return intensity;
	}

	
	public static class Builder {
		
		private int id;
		
		private String name;

		private int intensity;

		private boolean isEnabled;
		
		public Builder(String name, int intensity, boolean isEnabled) {
			Utils.validateNullOrBlank(new SuperheroException("power 'name' is mandatory", null), name);
			this.name = name;
			this.intensity = intensity;
			this.isEnabled = isEnabled;
		}
		
		public Builder withId(int id) {
			this.id = id;
			return this;
		}
		
		public Power build() {
			return new Power(this.id, this.name, this.intensity, this.isEnabled);
		}
		
		
	}

}
