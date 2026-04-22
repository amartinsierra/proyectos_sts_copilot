package com.caixaba.microestudiantes.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import com.caixaba.microestudiantes.entity.Alumno;
import com.caixaba.microestudiantes.mapper.MapeadorEstudiante;
import com.capgemini.microestudiantes.api.domain.Estudiante;
@Service
public class EstudiantesServiceImpl implements EstudiantesService {
	private static final String ALUMNOS_PATH = "/alumnos";

	private final RestClient restClient;
	private final MapeadorEstudiante mapeadorEstudiante;

	public EstudiantesServiceImpl(RestClient restClient, MapeadorEstudiante mapeadorEstudiante) {
		this.restClient = restClient;
		this.mapeadorEstudiante = mapeadorEstudiante;
	}

	@Override
	public List<Estudiante> findByRangoNotas(Integer min, Integer max) {
		
		List<Alumno> alumnos = Arrays.asList(restClient.get()
				.uri(ALUMNOS_PATH)
				.retrieve()
				.body(Alumno[].class));
		return alumnos.stream()
				.filter(alumno -> alumno.getCalificacion() >= min && alumno.getCalificacion() <= max)
				.map(mapeadorEstudiante::toDto)
				.toList();
		
	}

	@Override
	public Optional<Alumno> createEstudiante(Estudiante estudiante) {
		try {
			Alumno alumno = mapeadorEstudiante.toEntity(estudiante);
			Alumno created = restClient.post()
					.uri(ALUMNOS_PATH)
					.contentType(MediaType.APPLICATION_JSON)
					.body(alumno)
					.retrieve()
					.body(Alumno.class);
			return Optional.ofNullable(created);
		} catch (RestClientException e) {
			return Optional.empty();
		}
	}

}
