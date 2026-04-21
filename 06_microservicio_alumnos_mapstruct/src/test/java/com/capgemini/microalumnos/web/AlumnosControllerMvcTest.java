package com.capgemini.microalumnos.web;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.capgemini.microalumnos.api.domain.Alumno;
import com.caixaba.absis.microalumnos.service.AlumnosService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
class AlumnosControllerMvcTest {

    private MockMvc mockMvc;

    @Mock
    private AlumnosService alumnosService;

    private AlumnosController controller;

    private final ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    void setup() {
        controller = new AlumnosController(alumnosService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void postCreateAlumno_serviceReturnsTrue_shouldReturn201() throws Exception {
        Alumno alumno = new Alumno();
        alumno.setDni("12345678A");
        alumno.setNombre("Juan");
        alumno.setEmail("j@e.com");
        alumno.setCurso("1A");

        when(alumnosService.createAlumno(any(Alumno.class))).thenReturn(true);

        mockMvc.perform(post("/alumnos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(alumno)))
                .andExpect(status().isCreated());

        verify(alumnosService).createAlumno(any(Alumno.class));
    }

    @Test
    void postCreateAlumno_serviceReturnsFalse_shouldReturn400() throws Exception {
        Alumno alumno = new Alumno();
        alumno.setDni("12345678A");
        alumno.setNombre("Juan");

        when(alumnosService.createAlumno(any(Alumno.class))).thenReturn(false);

        mockMvc.perform(post("/alumnos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(alumno)))
                .andExpect(status().isBadRequest());

        verify(alumnosService).createAlumno(any(Alumno.class));
    }

    @Test
    void getAllAlumnos_shouldReturn200AndList() throws Exception {
        Alumno a1 = new Alumno();
        a1.setDni("1A");
        a1.setNombre("A");
        Alumno a2 = new Alumno();
        a2.setDni("2B");
        a2.setNombre("B");
        List<Alumno> list = Arrays.asList(a1, a2);
        when(alumnosService.getAllAlumnos()).thenReturn(list);

        mockMvc.perform(get("/alumnos").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].dni").value("1A"))
                .andExpect(jsonPath("$[1].dni").value("2B"));

        verify(alumnosService).getAllAlumnos();
    }

    @Test
    void getAlumnoByDni_whenFound_shouldReturn200AndAlumno() throws Exception {
        String dni = "1111A";
        Alumno alumno = new Alumno();
        alumno.setDni(dni);
        alumno.setNombre("X");
        when(alumnosService.getAlumnoByDni(dni)).thenReturn(alumno);

        mockMvc.perform(get("/alumnos/{dni}", dni).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.dni").value(dni))
                .andExpect(jsonPath("$.nombre").value("X"));

        verify(alumnosService).getAlumnoByDni(dni);
    }

    @Test
    void getAlumnoByDni_whenNotFound_shouldReturn404() throws Exception {
        String dni = "0000X";
        when(alumnosService.getAlumnoByDni(dni)).thenReturn(null);

        mockMvc.perform(get("/alumnos/{dni}", dni).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(alumnosService).getAlumnoByDni(dni);
    }

    @Test
    void deleteAlumno_whenFound_shouldReturn204() throws Exception {
        String dni = "1234X";
        when(alumnosService.deleteAlumno(dni)).thenReturn(true);

        mockMvc.perform(delete("/alumnos/{dni}", dni)).andExpect(status().isNoContent());

        verify(alumnosService).deleteAlumno(dni);
    }

    @Test
    void deleteAlumno_whenNotFound_shouldReturn404() throws Exception {
        String dni = "9999Z";
        when(alumnosService.deleteAlumno(dni)).thenReturn(false);

        mockMvc.perform(delete("/alumnos/{dni}", dni)).andExpect(status().isNotFound());

        verify(alumnosService).deleteAlumno(dni);
    }

    @Test
    void getAlumnosByCurso_whenFound_shouldReturn200AndList() throws Exception {
        String curso = "1A";
        Alumno a = new Alumno();
        a.setDni("d1");
        a.setNombre("Name");
        List<Alumno> list = Arrays.asList(a);
        when(alumnosService.getAlumnosByCurso(curso)).thenReturn(list);

        mockMvc.perform(get("/alumnos/curso").param("curso", curso).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].dni").value("d1"));

        verify(alumnosService).getAlumnosByCurso(curso);
    }

    @Test
    void getAlumnosByCurso_whenEmpty_shouldReturn404() throws Exception {
        String curso = "no-existe";
        when(alumnosService.getAlumnosByCurso(curso)).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/alumnos/curso").param("curso", curso).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(alumnosService).getAlumnosByCurso(curso);
    }

    @Test
    void getCursos_shouldReturn200AndList() throws Exception {
        List<String> cursos = Arrays.asList("1A", "2B");
        when(alumnosService.getCursos()).thenReturn(cursos);

        mockMvc.perform(get("/cursos").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0]").value("1A"));

        verify(alumnosService).getCursos();
    }
}
