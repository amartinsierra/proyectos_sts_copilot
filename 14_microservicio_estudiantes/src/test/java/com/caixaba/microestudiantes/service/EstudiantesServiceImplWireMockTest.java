package com.caixaba.microestudiantes.service;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.web.client.RestClient;

import com.caixaba.microestudiantes.entity.Alumno;
import com.caixaba.microestudiantes.mapper.MapeadorEstudiante;
import com.capgemini.microestudiantes.api.domain.Estudiante;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;

@SpringBootTest(classes = EstudiantesServiceImplWireMockTest.TestConfig.class)
class EstudiantesServiceImplWireMockTest {

	private static final String ALUMNOS_PATH = "/alumnos";

	@RegisterExtension
	static final WireMockExtension WIREMOCK = WireMockExtension.newInstance()
			.options(WireMockConfiguration.wireMockConfig().dynamicPort())
			.build();

	@DynamicPropertySource
	static void properties(DynamicPropertyRegistry registry) {
		registry.add("alumnos.base-url", () -> "http://localhost:" + WIREMOCK.getPort());
	}

	@Configuration
	static class TestConfig {
		@Bean
		RestClient restClient(TestProperties props) {
			return RestClient.builder()
					.baseUrl(props.getAlumnosBaseUrl())
					.build();
		}

		@Bean
		TestProperties testProperties(org.springframework.core.env.Environment env) {
			return new TestProperties(env.getProperty("alumnos.base-url"));
		}

		@Bean
		ObjectMapper objectMapper() {
			return new ObjectMapper();
		}

		@Bean
		EstudiantesService estudiantesService(RestClient restClient, MapeadorEstudiante mapeadorEstudiante) {
			return new EstudiantesServiceImpl(restClient, mapeadorEstudiante);
		}

		@Bean
		MapeadorEstudiante mapeadorEstudiante() {
			return org.mapstruct.factory.Mappers.getMapper(MapeadorEstudiante.class);
		}
	}

	static class TestProperties {
		private final String alumnosBaseUrl;

		TestProperties(String alumnosBaseUrl) {
			this.alumnosBaseUrl = alumnosBaseUrl;
		}

		String getAlumnosBaseUrl() {
			return alumnosBaseUrl;
		}
	}

	@Autowired
	private EstudiantesService estudiantesService;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void findByRangoNotas_filtraYMapea_conMapStruct() throws Exception {
		Alumno a1 = new Alumno("Ana", "111A", "ana@test.com", "1A", 3f);
		Alumno a2 = new Alumno("Luis", "222B", "luis@test.com", "2B", 7f);
		Alumno a3 = new Alumno("Marta", "333C", "marta@test.com", "3C", 10f);

		String json = objectMapper.writeValueAsString(new Alumno[] { a1, a2, a3 });

		WIREMOCK.stubFor(get(urlEqualTo(ALUMNOS_PATH))
				.willReturn(aResponse()
						.withStatus(200)
						.withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
						.withBody(json)));

		List<Estudiante> result = estudiantesService.findByRangoNotas(5, 10);
		assertEquals(2, result.size());

		// Filtrado por rango y mapeo calificacion -> nota
		assertTrue(result.stream().allMatch(e -> e.getNota() >= 5f && e.getNota() <= 0f));
		assertTrue(result.stream().anyMatch(e -> "Luis".equals(e.getNombre()) && e.getNota() == 7f));
		assertTrue(result.stream().anyMatch(e -> "Marta".equals(e.getNombre()) && e.getNota() == 10f));
	}

	@Test
	void createEstudiante_devuelveOptionalPresent_siOK() {
		Estudiante estudiante = new Estudiante();
		estudiante.setNombre("Eva");
		estudiante.setNota(9f);

		String createdJson = "{\"nombre\":\"Eva\",\"dni\":\"444D\",\"email\":\"eva@test.com\",\"curso\":\"4D\",\"calificacion\":9.0}";

		WIREMOCK.stubFor(post(urlEqualTo(ALUMNOS_PATH))
				.withHeader(HttpHeaders.CONTENT_TYPE, equalTo(MediaType.APPLICATION_JSON_VALUE))
				.willReturn(aResponse()
						.withStatus(201)
						.withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
						.withBody(createdJson)));

		Optional<Alumno> result = estudiantesService.createEstudiante(estudiante);
		assertTrue(result.isPresent());

		Alumno alumno = result.orElseThrow();
		assertNotNull(alumno);
		assertEquals("Eva", alumno.getNombre());
		assertEquals(9f, alumno.getCalificacion());
	}

	@Test
	void createEstudiante_devuelveOptionalEmpty_si500() {
		Estudiante estudiante = new Estudiante();
		estudiante.setNombre("Error");
		estudiante.setNota(1f);

		WIREMOCK.stubFor(post(urlEqualTo(ALUMNOS_PATH))
				.willReturn(aResponse().withStatus(500)));

		Optional<Alumno> result = estudiantesService.createEstudiante(estudiante);
		assertFalse(result.isPresent());
	}
}
