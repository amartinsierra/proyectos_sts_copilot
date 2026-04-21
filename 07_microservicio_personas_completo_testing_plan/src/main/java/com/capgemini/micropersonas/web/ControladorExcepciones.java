package com.capgemini.micropersonas.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.capgemini.micropersonas.exceptions.PersonaNotFoundException;

@ControllerAdvice
public class ControladorExcepciones {
	//configura un controlador de excepciones para manejar las excepciones de tipo PersonaNotFoundException
	//y lanzar una respuesta que incluya un objeto ApiError con el mensaje de error y el código de estado HTTP 404
	@ExceptionHandler(PersonaNotFoundException.class)
	public ResponseEntity<ApiError> handlePersonaNotFoundException(PersonaNotFoundException ex) {
		ApiError apiError = new ApiError(ex.getMessage(), HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
	}
	
	//configura un controlador de excepciones para manejar la IllegalArgumentException y lanzar una respuesta que incluya 
	//un objeto ApiError con el mensaje de error "No se ademite id repetido" y el código de estado HTTP 409
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ApiError> handleIllegalArgumentException(IllegalArgumentException ex) {
		ApiError apiError = new ApiError("No se ademite id repetido", HttpStatus.CONFLICT.value());
		return new ResponseEntity<>(apiError, HttpStatus.CONFLICT);
	}
}
