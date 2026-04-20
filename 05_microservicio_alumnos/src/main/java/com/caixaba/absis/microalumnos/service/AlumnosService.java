package com.caixaba.absis.microalumnos.service;

import java.util.List;

import com.capgemini.microalumnos.api.domain.Alumno;

public interface AlumnosService {
	/**
	 * Crea un nuevo alumno.
	 * @param alumno el alumno a crear
	 * @return true si el alumno se ha creado correctamente, false si el alumno no se ha podido crear
	 */
	boolean createAlumno(Alumno alumno);
	
	/**
	 * Elimina un alumno por su DNI.
	 * @param dni el DNI del alumno a eliminar
	 * @return true si el alumno se ha eliminado correctamente, false si el alumno no se ha encontrado
	 */
	boolean deleteAlumno(String dni);
	
	/**
	 * Devuelve una lista con todos los alumnos.
	 * @return una lista con todos los alumnos
	 */
	List<Alumno> getAllAlumnos();
	
	/**
	 * Devuelve un alumno por su DNI.
	 * @param dni el DNI del alumno a buscar
	 * @return el alumno encontrado, o null si no se ha encontrado ningún alumno con ese DNI
	 */
	Alumno getAlumnoByDni(String dni);
	
	
	//método que devuelve todos los alumnos de un curso determinado
	/**
	 * Devuelve una lista con todos los alumnos de un curso determinado.
	 * @param curso el curso del que se quieren obtener los alumnos
	 * @return una lista con todos los alumnos del curso indicado
	 */
	List<Alumno> getAlumnosByCurso(String curso);
	
	//método que devuelve una lista con los cursos disponibles
	/**
	 * Devuelve una lista con los cursos disponibles.
	 * @return una lista con los cursos disponibles
	 */
	List<String> getCursos();
}
