package com.caixaba.absis.microalumnos.mapper;

public class AlumnoMapper {
	// Esta clase se encarga de convertir entre AlumnoEntity y Alumno (la clase generada por OpenAPI).
	// Aquí puedes implementar métodos para mapear de AlumnoEntity a Alumno y viceversa.
	//empieza con el método toDto, que convierte un AlumnoEntity a un Alumno.
	//ten en cuenta que el campo nota de AlumnoEntity es un Double, y se corresponde con el campo calificacion de Alumno que es Float, por lo que hay que hacer una conversión de tipos.
	public static com.capgemini.microalumnos.api.domain.Alumno toDto(com.caixaba.absis.microalumnos.entity.AlumnoEntity entity) {
		if (entity == null) {
			return null;
		}
		com.capgemini.microalumnos.api.domain.Alumno dto = new com.capgemini.microalumnos.api.domain.Alumno();
		dto.setDni(entity.getDni());
		dto.setNombre(entity.getNombre());
		dto.setEmail(entity.getEmail());
		dto.setCurso(entity.getCurso());
		dto.setCalificacion(entity.getNota() != null ? entity.getNota().floatValue() : null);
		return dto;
	}
	//método toEntity, que convierte un Alumno a un AlumnoEntity.
	public static com.caixaba.absis.microalumnos.entity.AlumnoEntity toEntity(com.capgemini.microalumnos.api.domain.Alumno dto) {
		if (dto == null) {
			return null;
		}
		com.caixaba.absis.microalumnos.entity.AlumnoEntity entity = new com.caixaba.absis.microalumnos.entity.AlumnoEntity();
		entity.setDni(dto.getDni());
		entity.setNombre(dto.getNombre());
		entity.setEmail(dto.getEmail());
		entity.setCurso(dto.getCurso());
		entity.setNota(dto.getCalificacion() != null ? dto.getCalificacion().doubleValue() : null);
		return entity;
	}
	
}
