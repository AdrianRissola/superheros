package com.w2m.superheros.application.model.exceptions;

import org.springframework.http.HttpStatus;

public class SuperheroException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private HttpStatus httpStatus;

	public SuperheroException(String errorMessage, HttpStatus httpStatus) {
		super(errorMessage);
		this.setHttpStatus(httpStatus);
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

}
