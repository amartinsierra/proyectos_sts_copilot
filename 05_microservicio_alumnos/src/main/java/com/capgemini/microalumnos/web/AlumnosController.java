package com.capgemini.microalumnos.web;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.caixaba.absis.microalumnos.service.AlumnosService;
import com.capgemini.microalumnos.api.domain.Alumno;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class AlumnosController implements com.capgemini.microalumnos.api.AlumnosApi {

	//crea una variable de tipo AlumnosService e inyectala por constructor
	
		AlumnosService alumnosService;
		public AlumnosController(AlumnosService alumnosService) {
			this.alumnosService = alumnosService;
		}

		@Override
		public ResponseEntity<Void> createAlumno(@Valid Alumno alumno) {
			//utiliza el servicio para crear el alumno y devuelve un 201 indicando que el alumno se ha creado correctamente
			//si te devuelve false, devuelve un 400 indicando que el alumno no se ha podido crear
			if (alumnosService.createAlumno(alumno)) {
				return ResponseEntity.status(201).build();
			} else {
				return ResponseEntity.badRequest().build();
			}
			
		}

		@Override
		public ResponseEntity<Void> deleteAlumno(String dni) {
			//utiliza el servicio para eliminar el alumno y devuelve un 204 indicando que el alumno se ha eliminado correctamente
			//si el servicio devuelve false, devuelve un 404 indicando que el alumno no se ha encontrado
			if (alumnosService.deleteAlumno(dni)) {
				return ResponseEntity.noContent().build();
			} else {
				return ResponseEntity.notFound().build();
			}
		}
		@Override
		public ResponseEntity<List<Alumno>> getAllAlumnos() {
			//devuelve un 200 con la lista de alumnos obtenida del servicio
			return ResponseEntity.ok(alumnosService.getAllAlumnos());
		}

		@Override
		public ResponseEntity<Alumno> getAlumnoByDni(String dni) {
			//devuelve un 200 con el alumno obtenido del servicio si existe, si no existe, devuelve un 404 indicando que el alumno no se ha encontrado
			Alumno alumno = alumnosService.getAlumnoByDni(dni);
			if (alumno != null) {
				return ResponseEntity.ok(alumno);
			} else {
				return ResponseEntity.notFound().build();
			}
		}

		@Override
		public ResponseEntity<List<Alumno>> getAlumnosByCurso(@NotNull @Valid String curso) {
			//devuelve un 200 con una lista de alumnos obtenida del servicio si existen, si no existen, devuelve un 404 indicando que no se han encontrado alumnos
			List<Alumno> alumnos = alumnosService.getAlumnosByCurso(curso);
			if (!alumnos.isEmpty()) {
				return ResponseEntity.ok(alumnos);
			} else {
				return ResponseEntity.notFound().build();
			}
		}

		

		@Override
		public ResponseEntity<List<String>> getCursos() {
			//devuelve un 200 con una lista de cursos obtenida del servicio
			List<String> cursos = alumnosService.getCursos();
			return ResponseEntity.ok(cursos);
		}

}
