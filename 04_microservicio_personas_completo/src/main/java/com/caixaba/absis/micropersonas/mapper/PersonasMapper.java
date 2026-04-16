package com.caixaba.absis.micropersonas.mapper;

import com.caixaba.absis.micropersonas.entity.PersonaEntity;
import com.capgemini.micropersonas.api.domain.Persona;

public class PersonasMapper {
	//Crea un método público y estático transforme que reciba un objeto de tipo PersonaEntity y devuelva un objeto de tipo Persona
	//el nombre del método será toDto. No utilices nombres cualificados para los tipos de datos, asume que ya se han importado las clases necesarias.
	public static Persona toDto(PersonaEntity personaEntity) {
		//crea un nuevo objeto de tipo Persona utilizando el constructor que recibe los atributos id, nombre, edad y email
		//y asigna los valores correspondientes del objeto personaEntity a cada atributo del nuevo objeto Persona
		return new Persona(personaEntity.getId(), personaEntity.getNombre(), personaEntity.getEdad(), personaEntity.getEmail());
	}
	//Crea un método público y estático transforme que reciba un objeto de tipo Persona y devuelva un objeto de tipo PersonaEntity
	//el nombre del método será toEntity. No utilices nombres cualificados para los tipos de datos, asume que ya se han importado las clases necesarias.
	public static PersonaEntity toEntity(Persona persona) {
		//crea un nuevo objeto de tipo PersonaEntity utilizando el constructor que recibe los atributos id, nombre, edad y email
		//y asigna los valores correspondientes del objeto persona a cada atributo del nuevo objeto PersonaEntity
		return new PersonaEntity(persona.getId(), persona.getNombre(), persona.getEdad(), persona.getEmail());
	}
}
