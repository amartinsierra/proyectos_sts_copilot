package com.caixaba.absis.micropersonas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.caixaba.absis.micropersonas.mapper.PersonasMapper;
import com.caixaba.absis.micropersonas.repository.PersonasRepository;
import com.capgemini.micropersonas.api.domain.Persona;
import com.capgemini.micropersonas.exceptions.PersonaNotFoundException;

/**
 * Servicio de negocio para operaciones sobre Personas.
 *
 * <p>
 * Esta implementación delega la persistencia en un {@link PersonasRepository}
 * y convierte entre la entidad persistente y el dominio {@link Persona} usando
 * {@link PersonasMapper}.
 * </p>
 *
 * <p>
 * Observaciones importantes:
 * - El repositorio trabaja con entidades (persistencia) y este servicio expone
 *   objetos de dominio {@link Persona} (DTOs) mediante el mapeador.
 * - Algunos métodos devuelven {@link Optional}{@code <Persona>} y otros devuelven
 *   directamente {@link Persona}. En contexto de diseño, considere devolver
 *   {@link Optional} cuando la ausencia de valor pueda representarse sin lanzar
 *   excepciones. Sin embargo, este servicio lanza excepciones específicas cuando
 *   una persona no es encontrada (por ejemplo {@link PersonaNotFoundException}),
 *   según se describe en cada método.
 * </p>
 *
 * @since 1.0
 */
@Service
public class PersonasServiceImpl implements PersonasService {
	/**
	 * Repositorio que gestiona las operaciones de persistencia de Persona.
	 *
	 * <p>
	 * Se inyecta por constructor (inyección por constructor recomendada para facilitar
	 * pruebas y promover la inmutabilidad de la dependencia).
	 * </p>
	 */
	private PersonasRepository personasRepository;

	/**
	 * Constructor que recibe el repositorio mediante inyección por constructor.
	 *
	 * @param personasRepository repositorio para acceder a las operaciones CRUD sobre personas
	 */
	public PersonasServiceImpl(PersonasRepository personasRepository) {
		this.personasRepository = personasRepository;
	}

	/**
	 * Obtiene todas las personas almacenadas.
	 *
	 * <p>
	 * Recupera todas las entidades desde el repositorio y las transforma a objetos
	 * de dominio {@link Persona} usando {@link PersonasMapper#toDto}.
	 * </p>
	 *
	 * @return lista de {@link Persona} representando todas las personas en el repositorio.
	 *         Si no hay registros, devuelve una lista vacía.
	 */
	@Override
	public List<Persona> getAllPersonas() {
		// Utiliza el repositorio para obtener la lista de personas, tranformando de entidad a dominio
		// utiliza para ello el método toDto de la clase PersonaMapper
		return personasRepository.findAll().stream()
				.map(PersonasMapper::toDto)
				.toList();
	}

	/**
	 * Obtiene una persona por su identificador.
	 *
	 * <p>
	 * Intenta buscar la persona por id en el repositorio, convierte la entidad a DTO
	 * mediante {@link PersonasMapper#toDto} y devuelve un {@link Optional}{@code <Persona>}.
	 * Si la persona con el id especificado no existe, se lanza
	 * {@link PersonaNotFoundException}.
	 * </p>
	 *
	 * <p>
	 * Nota: el uso combinado de {@link Optional} y la excepción hace que el método
	 * normalmente devuelva un {@link Optional} cuando existe y lance excepción cuando no.
	 * </p>
	 *
	 * @param id identificador de la persona a buscar
	 * @return {@link Optional}{@code <Persona>} con la persona encontrada
	 * @throws PersonaNotFoundException si no existe ninguna persona con el id proporcionado
	 */
	@Override
	public Optional<Persona> getPersonaById(int id) {
		// Utiliza el repositorio para devolver la persona con el id especificado, si 
		// la persona con id no existe lanza una excepción PersonaNotFoundException
		return personasRepository.findById(id)
				.map(PersonasMapper::toDto)
				.or(() -> {
					throw new PersonaNotFoundException(id);
				});
	}

	/**
	 * Obtiene las personas cuya edad está en el rango [min, max] (ambos inclusive).
	 *
	 * <p>
	 * Utiliza el método personalizado {@code findByEdadBetween} del repositorio para
	 * recuperar las entidades dentro del rango de edad y las transforma a DTOs.
	 * </p>
	 *
	 * @param min edad mínima (inclusive)
	 * @param max edad máxima (inclusive)
	 * @return lista de {@link Persona} cuya edad está entre {@code min} y {@code max}.
	 *         Si no hay resultados, devuelve una lista vacía.
	 */
	@Override
	public List<Persona> getPersonasByEdadRange(int min, int max) {
		// devuelve la lista de personas que tienen una edad entre min y max, ambos inclusive
		return personasRepository.findByEdadBetween(min, max).stream()
				.map(PersonasMapper::toDto)
				.toList();
	}

	/**
	 * Crea una nueva persona.
	 *
	 * <p>
	 * Comprueba que no exista ya una persona con el mismo id mediante
	 * {@link PersonasRepository#existsById}. Si ya existe, lanza
	 * {@link IllegalArgumentException}. En caso contrario convierte el DTO de dominio
	 * a entidad con {@link PersonasMapper#toEntity}, guarda la entidad y devuelve el DTO
	 * resultante (mapeado con {@link PersonasMapper#toDto}).
	 * </p>
	 *
	 * @param persona DTO {@link Persona} que representa la persona a crear. Debe contener un id.
	 * @return el {@link Persona} creado (resultado del guardado y mapeo desde la entidad).
	 * @throws IllegalArgumentException si ya existe una persona con el mismo id
	 */
	@Override
	public Persona createPersona(Persona persona) {
		// añade la persona a la lista de personas y devuelve la persona creada. No deber permitir
		// id repetidos, si la persona con el id ya existe lanza una excepción IllegalArgumentException
		if (personasRepository.existsById(persona.getId())) {
			throw new IllegalArgumentException("La persona con id " + persona.getId() + " ya existe");
		}
		return PersonasMapper.toDto(personasRepository.save(PersonasMapper.toEntity(persona)));
	}

	/**
	 * Elimina la persona con el id especificado y devuelve la persona eliminada.
	 *
	 * <p>
	 * Busca la persona por id y, si no existe, lanza {@link PersonaNotFoundException}.
	 * Si existe, mapea la entidad a DTO para devolverla, elimina la entidad mediante
	 * {@link PersonasRepository#deleteById} y devuelve el DTO de la persona eliminada.
	 * </p>
	 *
	 * @param id identificador de la persona a eliminar
	 * @return el {@link Persona} eliminado
	 * @throws PersonaNotFoundException si no existe ninguna persona con el id proporcionado
	 */
	@Override
	public Persona deletePersona(int id) {
		// elimina la persona con el id especificado de la lista de personas y devuelve la persona eliminada. 
		// Si la persona con el id no existe lanza una excepción PersonaNotFoundException
		Persona persona = personasRepository.findById(id)
				.map(PersonasMapper::toDto)
				.orElseThrow(() -> new PersonaNotFoundException());
		personasRepository.deleteById(id);
		return persona;
	}

}
