package com.capgemini.micropersonas.exceptions;

public class PersonaNotFoundException extends RuntimeException {
	public PersonaNotFoundException(int id) {
		super("Persona con id " + id + " no encontrada");
	}

}
