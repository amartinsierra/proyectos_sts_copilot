package com.caixaba.absis.microalumnos.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.capgemini.microalumnos.api.domain.Alumno;

//Tienes que crear la interfaz AlumnoMapper que se encargue de convertir entre AlumnoEntity y Alumno (la clase generada por OpenAPI), utilizando MapStruct. Para ello, sigue los siguientes pasos:
//1. Anota la interfaz con @Mapper para indicar que es un mapeador de MapStruct.
//2. Define un método toDto que convierta un AlumnoEntity a un Alumno. Ten en cuenta que el campo nota de AlumnoEntity es un Double, y se corresponde con el campo calificacion de Alumno que es Float, por lo que hay que hacer una conversión de tipos.
//3. Define un método toEntity que convierta un Alumno a un AlumnoEntity. Ten en cuenta que el campo calificacion de Alumno es un Float, y se corresponde con el campo nota de AlumnoEntity que es Double, por lo que hay que hacer una conversión de tipos.
@Mapper(componentModel = "spring")
public interface AlumnoMapper{
	@Mapping(source = "nota", target = "calificacion")
	Alumno toDto(com.caixaba.absis.microalumnos.entity.AlumnoEntity entity);
	@Mapping(source = "calificacion", target = "nota")
	com.caixaba.absis.microalumnos.entity.AlumnoEntity toEntity(Alumno dto);
}