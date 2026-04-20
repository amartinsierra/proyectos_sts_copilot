package com.caixaba.absis.microalumnos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.caixaba.absis.microalumnos.mapper.AlumnoMapper;
import com.caixaba.absis.microalumnos.repository.AlumnosRepository;
import com.capgemini.microalumnos.api.domain.Alumno;
@Service
public class AlumnosServiceImpl implements AlumnosService {
	//decara dos variables alumnosRepository de tipo AlumnosRepository y alumnosMapper de tipo AlumnosMapper e inyectala por constructor
	//no quiero lista de alumnos, quiero que el servicio se comunique con la base de datos a través del repositorio
	
	AlumnosRepository alumnosRepository;
	AlumnoMapper alumnosMapper;
	public AlumnosServiceImpl(AlumnosRepository alumnosRepository, AlumnoMapper alumnosMapper) {
		this.alumnosRepository = alumnosRepository;
		this.alumnosMapper = alumnosMapper;
	}

	@Override
	public boolean createAlumno(Alumno alumno) {
		//añade el alumno a la lista de alumnos y devuelve true indicando que el alumno se ha creado correctamente
		//si ya existe un alumno con ese dni, no lo añade y devuelve false indicando que el alumno no se ha podido crear
		//utiliza el mapeador para convertir el objeto Alumno a AlumnoEntity antes de guardarlo en la base de datos
		if (alumnosRepository.existsById(alumno.getDni())) {
			return false;
		} else {
			alumnosRepository.save(AlumnoMapper.toEntity(alumno));
			return true;
		}
		
	}

	@Override
	public boolean deleteAlumno(String dni) {
		//elimina el alumno de la lista de alumnos y devuelve true indicando que el alumno se ha eliminado correctamente
		//si no existe un alumno con ese dni, devuelve false indicando que el alumno no se ha podido eliminar
		if (alumnosRepository.existsById(dni)) {
			alumnosRepository.deleteById(dni);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<Alumno> getAllAlumnos() {
		//devuelve la lista de alumnos. Utiliza el mapeador para convertir la lista de AlumnoEntity a una lista de Alumno antes de devolverla
		return alumnosRepository.findAll().stream().map(AlumnoMapper::toDto).toList();
	}

	@Override
	public Alumno getAlumnoByDni(String dni) {
		//busca el alumno en la lista de alumnos por su DNI. Utiliza el mapeador para convertir el objeto AlumnoEntity a Alumno antes de devolverlo. Si no encuentra el alumno, devuelve null
		return alumnosRepository.findById(dni).map(AlumnoMapper::toDto).orElse(null);
	}

	@Override
	public List<Alumno> getAlumnosByCurso(String curso) {
		//devuelve una lista con todos los alumnos de un curso determinado
		return alumnosRepository.findByCurso(curso).stream().map(AlumnoMapper::toDto).toList();
		
	}

	@Override
	public List<String> getCursos() {
		//devuelve una lista con los cursos disponibles, sin duplicados		
		return alumnosRepository.findAllCursos();
	}

}
