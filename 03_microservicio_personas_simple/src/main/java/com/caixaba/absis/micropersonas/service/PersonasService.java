package com.caixaba.absis.micropersonas.service;

import java.util.List;
import java.util.Optional;

import com.capgemini.micropersonas.api.domain.Persona;

public interface PersonasService {
	List<Persona> getAllPersonas();
	Optional<Persona> getPersonaById(int id);
}
