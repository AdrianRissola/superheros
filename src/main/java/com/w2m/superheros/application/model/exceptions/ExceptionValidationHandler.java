package com.w2m.superheros.application.model.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionValidationHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(ExceptionValidationHandler.class);
	public static final String MESSAGE = "message";
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Map<String, String> processValidationError(MethodArgumentNotValidException methodArgumentNotValidException) {
		var errors = new HashMap<String, String>();
		var messages = new HashMap<String, String>();
		methodArgumentNotValidException.getBindingResult().getAllErrors().forEach((error) -> {
			var fieldName = ((FieldError) error).getField();
			var errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		logger.error("Cause: {}", errors.toString());
		messages.put(MESSAGE, errors.toString());
		return messages;
	}
	
	@ExceptionHandler(SuperheroException.class)
	public final ResponseEntity<String> handleSuperheroManagerException(SuperheroException superheroException) {
		logger.error("Cause: {}", superheroException.getMessage());
		return new ResponseEntity<String>(superheroException.getMessage(), superheroException.getHttpStatus());
	}
	
	
	@ExceptionHandler(Exception.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public final String handleException(Exception exception) {
		logger.error("Cause: {}", exception.getMessage());
		return exception.getMessage();
	}

}
