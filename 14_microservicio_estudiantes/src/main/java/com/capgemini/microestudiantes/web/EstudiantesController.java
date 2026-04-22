package com.capgemini.microestudiantes.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.caixaba.microestudiantes.service.EstudiantesService;
import com.capgemini.microestudiantes.api.EstudiantesApi;
import com.capgemini.microestudiantes.api.domain.Estudiante;

import jakarta.validation.Valid;
@RestController
public class EstudiantesController implements EstudiantesApi {

	private final EstudiantesService estudiantesService;

	public EstudiantesController(EstudiantesService estudiantesService) {
		this.estudiantesService = estudiantesService;
	}

	@Override
	public ResponseEntity<Void> createEstudiante(@Valid Estudiante estudiante) {
		return estudiantesService.createEstudiante(estudiante)
				.map(alumnoCreado -> new ResponseEntity<Void>(HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<Void>(HttpStatus.CONFLICT));
	}

	@Override
	public ResponseEntity<List<Estudiante>> getEstudiantesByRangoNotas(Integer min, Integer max) {
		List<Estudiante> estudiantes = estudiantesService.findByRangoNotas(min, max);
		return ResponseEntity.ok(estudiantes);
	}
}