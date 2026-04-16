package com.caixaba.absis.micropersonas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.caixaba.absis.micropersonas.mapper.PersonasMapper;
import com.caixaba.absis.micropersonas.repository.PersonasRepository;
import com.capgemini.micropersonas.api.domain.Persona;
import com.capgemini.micropersonas.exceptions.PersonaNotFoundException;

@Service
public class PersonasServiceImpl implements PersonasService {
	//Declara una variable de tipo PersonasRepository e inyectala por constructor
	private PersonasRepository personasRepository;
	public PersonasServiceImpl(PersonasRepository personasRepository) {
		this.personasRepository = personasRepository;
	}
	@Override
	public List<Persona> getAllPersonas() {
		// Utiliza el repositorio para obtener la lista de personas, tranformando de entidad a dominio
		//utiliza para ello el método toDto de la clase PersonaMapper
		return personasRepository.findAll().stream()
				.map(PersonasMapper::toDto)
				.toList();
		
	}

	@Override
	public Optional<Persona> getPersonaById(int id) {
		// Utiliza el repositorio para devolver la persona con el id especificado, si 
		//la personas con id no existe lanza una excepción PersonaNotFoundException
		return personasRepository.findById(id)
				.map(PersonasMapper::toDto)
				.or(() -> {
					throw new PersonaNotFoundException(id);
				});
			
		
	}

	@Override
	public List<Persona> getPersonasByEdadRange(int min, int max) {
		//devuelve la lista de personas que tienen una edad entre min y max, ambos inclusive
		return personasRepository.findByEdadBetween(min, max).stream()
				.map(PersonasMapper::toDto)
				.toList();
		
	}

	@Override
	public Persona createPersona(Persona persona) {
		//añade la persona a la lista de personas y devuelve la persona creada. No deber permitir
		//id repetidos, si la persona con el id ya existe lanza una excepción IllegalArgumentException
		if (personasRepository.existsById(persona.getId())) {
			throw new IllegalArgumentException("La persona con id " + persona.getId() + " ya existe");
		}
		return PersonasMapper.toDto(personasRepository.save(PersonasMapper.toEntity(persona)));
		
	}

	@Override
	public Persona deletePersona(int id) {
		//elimina la persona con el id especificado de la lista de personas y devuelve la persona eliminada. 
		//Si la persona con el id no existe lanza una excepción PersonaNotFoundException
		Persona persona = personasRepository.findById(id)
				.map(PersonasMapper::toDto)
				.orElseThrow(() -> new PersonaNotFoundException(id));
		personasRepository.deleteById(id);
		return persona;
		
	}

}
