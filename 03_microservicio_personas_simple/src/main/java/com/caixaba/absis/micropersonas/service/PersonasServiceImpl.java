package com.caixaba.absis.micropersonas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.capgemini.micropersonas.api.domain.Persona;
import com.capgemini.micropersonas.exceptions.PersonaNotFoundException;

@Service
public class PersonasServiceImpl implements PersonasService {
	//Copia la lista de personas del controlador PersonasController
	private static List<Persona> personas=List.of(
			new Persona(1, "Juan", 30, "juangmail.com"),
			new Persona(2, "María", 25, "maria.es"),
			new Persona(3, "Pedro", 40, "pedro.es"),
			new Persona(4, "Ana", 35, "ana.es"),
			new Persona(5, "Luis", 20, "luis.es")
		);
	@Override
	public List<Persona> getAllPersonas() {
		// Devuelve una copia de la lista de personas para evitar modificar la lista original
		return List.copyOf(personas);
	}

	@Override
	public Optional<Persona> getPersonaById(int id) {
		// Devuelve la persona con el id especificado, si 
		//la personas con id no existe lanza una excepción PersonaNotFoundException
		return personas.stream()
				.filter(p -> p.getId() == id)
				.findFirst()
				.or(() -> {
					throw new PersonaNotFoundException(id);
				});
		
		
		
	}

}
