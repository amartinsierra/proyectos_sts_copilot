package com.caixaba.absis.microalumnos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.caixaba.absis.microalumnos.entity.AlumnoEntity;

//crea la interfaz AlumnosRepository que extienda de JpaRepository y tenga como entidad Alumno y como id String
//incluye los siguientes métodos:
//-busqueda de alumnos por curso
//-busqueda de todos los cursos disponibles sin duplicados

public interface AlumnosRepository extends JpaRepository<AlumnoEntity, String> {
	//busqueda de alumnos por curso
	public List<AlumnoEntity> findByCurso(String curso);
	
	//busqueda de todos los cursos disponibles sin duplicados. HAzlo con una consulta personalizada usando @Query
	//importa la interfaz Query de Spring Data JPA, no uses su nombre completo
	@Query("SELECT DISTINCT a.curso FROM AlumnoEntity a")
	public List<String> findAllCursos();
}
