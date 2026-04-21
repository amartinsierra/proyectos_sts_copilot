package com.caixaba.absis.microalumnos.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.caixaba.absis.microalumnos.entity.AlumnoEntity;
import com.caixaba.absis.microalumnos.repository.AlumnosRepository;
import com.caixaba.absis.microalumnos.mapper.AlumnoMapper;
import com.capgemini.microalumnos.api.domain.Alumno;

@ExtendWith(MockitoExtension.class)
public class AlumnosServiceImplTest {

    @Mock
    private AlumnosRepository alumnosRepository;

    @Mock
    private AlumnoMapper alumnoMapper;

    @InjectMocks
    private AlumnosServiceImpl alumnosService;

    @Test
    void testCreateAlumno_CreatesWhenNotExists() {
        // Arrange
        Alumno dto = mock(Alumno.class);
        when(dto.getDni()).thenReturn("dni-123");

        AlumnoEntity entity = new AlumnoEntity("dni-123", "Nombre", "email@x.com", "curso1", 9.0);
        when(alumnosRepository.existsById("dni-123")).thenReturn(false);
        when(alumnoMapper.toEntity(dto)).thenReturn(entity);

        // Act
        boolean result = alumnosService.createAlumno(dto);

        // Assert
        assertTrue(result, "createAlumno should return true when student does not exist");
        verify(alumnoMapper).toEntity(dto);
        verify(alumnosRepository).save(entity);
    }

    @Test
    void testCreateAlumno_ReturnsFalseWhenExists() {
        Alumno dto = mock(Alumno.class);
        when(dto.getDni()).thenReturn("dni-123");
        when(alumnosRepository.existsById("dni-123")).thenReturn(true);

        boolean result = alumnosService.createAlumno(dto);

        assertFalse(result);
        verify(alumnosRepository, never()).save(any());
    }

    @Test
    void testDeleteAlumno_DeletesWhenExists() {
        // Arrange
        String dni = "dni-456";
        when(alumnosRepository.existsById(dni)).thenReturn(true);

        // Act
        boolean result = alumnosService.deleteAlumno(dni);

        // Assert
        assertTrue(result, "deleteAlumno should return true when the student exists");
        verify(alumnosRepository).deleteById(dni);
    }

    @Test
    void testDeleteAlumno_ReturnsFalseWhenNotExists() {
        String dni = "no-existe";
        when(alumnosRepository.existsById(dni)).thenReturn(false);

        boolean result = alumnosService.deleteAlumno(dni);

        assertFalse(result);
        verify(alumnosRepository, never()).deleteById(anyString());
    }

    @Test
    void testGetAllAlumnos_ReturnsMappedList() {
        // Arrange
        AlumnoEntity e1 = new AlumnoEntity("d1", "A", "a@a.com", "c1", 7.5);
        AlumnoEntity e2 = new AlumnoEntity("d2", "B", "b@b.com", "c2", 8.0);

        Alumno dto1 = mock(Alumno.class);
        Alumno dto2 = mock(Alumno.class);

        when(alumnosRepository.findAll()).thenReturn(List.of(e1, e2));
        when(alumnoMapper.toDto(e1)).thenReturn(dto1);
        when(alumnoMapper.toDto(e2)).thenReturn(dto2);

        // Act
        List<Alumno> result = alumnosService.getAllAlumnos();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(dto1));
        assertTrue(result.contains(dto2));
        verify(alumnosRepository).findAll();
        verify(alumnoMapper).toDto(e1);
        verify(alumnoMapper).toDto(e2);
    }

    @Test
    void testGetAlumnoByDni_ReturnsMappedWhenFound() {
        // Arrange
        String dni = "dni-789";
        AlumnoEntity entity = new AlumnoEntity(dni, "Nombre", "n@x.com", "cursoX", 6.0);
        Alumno dto = mock(Alumno.class);

        when(alumnosRepository.findById(dni)).thenReturn(Optional.of(entity));
        when(alumnoMapper.toDto(entity)).thenReturn(dto);

        // Act
        Alumno result = alumnosService.getAlumnoByDni(dni);

        // Assert
        assertNotNull(result);
        assertEquals(dto, result);
        verify(alumnosRepository).findById(dni);
        verify(alumnoMapper).toDto(entity);
    }

    @Test
    void testGetAlumnoByDni_ReturnsNullWhenNotFound() {
        String dni = "no-existe";
        when(alumnosRepository.findById(dni)).thenReturn(Optional.empty());

        Alumno result = alumnosService.getAlumnoByDni(dni);

        assertNull(result);
    }

    @Test
    void testGetAlumnosByCurso_ReturnsMappedList() {
        // Arrange
        String curso = "cursoTest";
        AlumnoEntity e1 = new AlumnoEntity("dA", "A", "a@x", curso, 5.0);
        AlumnoEntity e2 = new AlumnoEntity("dB", "B", "b@x", curso, 6.0);

        Alumno dto1 = mock(Alumno.class);
        Alumno dto2 = mock(Alumno.class);

        when(alumnosRepository.findByCurso(curso)).thenReturn(List.of(e1, e2));
        when(alumnoMapper.toDto(e1)).thenReturn(dto1);
        when(alumnoMapper.toDto(e2)).thenReturn(dto2);

        // Act
        List<Alumno> result = alumnosService.getAlumnosByCurso(curso);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(dto1));
        assertTrue(result.contains(dto2));
        verify(alumnosRepository).findByCurso(curso);
        verify(alumnoMapper).toDto(e1);
        verify(alumnoMapper).toDto(e2);
    }

    @Test
    void testGetCursos_ReturnsListFromRepository() {
        // Arrange
        List<String> cursos = List.of("c1", "c2", "c3");
        when(alumnosRepository.findAllCursos()).thenReturn(cursos);

        // Act
        List<String> result = alumnosService.getCursos();

        // Assert
        assertNotNull(result);
        assertEquals(cursos, result);
        verify(alumnosRepository).findAllCursos();
    }
}
