package com.caixaba.microestudiantes.service;

import java.util.List;
import java.util.Optional;

import com.caixaba.microestudiantes.entity.Alumno;
import com.capgemini.microestudiantes.api.domain.Estudiante;

public interface EstudiantesService {
	List<Estudiante> findByRangoNotas(Integer min, Integer max);
	Optional<Alumno> createEstudiante(Estudiante estudiante);
}
