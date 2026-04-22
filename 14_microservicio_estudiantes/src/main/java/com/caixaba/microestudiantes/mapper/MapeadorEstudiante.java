package com.caixaba.microestudiantes.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.caixaba.microestudiantes.entity.Alumno;
import com.capgemini.microestudiantes.api.domain.Estudiante;

@Mapper(componentModel = "spring")
public interface MapeadorEstudiante {
	// calificacion (Alumno) <-> nota (Estudiante)
	@Mapping(source = "calificacion", target = "nota")
	Estudiante toDto(Alumno alumno);

	@Mapping(source = "nota", target = "calificacion")
	Alumno toEntity(Estudiante estudiante);
}
