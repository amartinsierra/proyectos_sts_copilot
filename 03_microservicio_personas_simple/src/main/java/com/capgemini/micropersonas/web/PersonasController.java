package com.capgemini.micropersonas.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.caixaba.absis.micropersonas.service.PersonasService;
import com.capgemini.micropersonas.api.PersonasApi;
import com.capgemini.micropersonas.api.domain.Persona;



@RestController
public class PersonasController implements PersonasApi {
	//Declara una variable de tipo PersonasService
	//e inyecta una instancia de PersonasServiceImpl en el constructor
	private PersonasService personasService;
	public PersonasController(PersonasService personasService) {
		this.personasService = personasService;
	}
	
	@Override
	public ResponseEntity<List<Persona>> personasGet() {
		//Devuelve la lista de personas obtenida del servicio PersonasService
		return ResponseEntity.ok(personasService.getAllPersonas());
	}

	@Override
	public ResponseEntity<Persona> personasIdGet(Integer id) {
		//Devuelve la persona con el id especificado obtenida del servicio PersonasService
		//no tienes que comprobar si la persona existe o no, el servicio PersonasService se encarga de lanzar una excepción si la persona no existe
		return ResponseEntity.ok(personasService.getPersonaById(id).orElseThrow());
		
	}

}
