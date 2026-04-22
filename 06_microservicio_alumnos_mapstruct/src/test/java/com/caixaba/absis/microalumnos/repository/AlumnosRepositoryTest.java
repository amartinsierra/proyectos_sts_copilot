package com.caixaba.absis.microalumnos.repository;

//importa la clase Assertions de JUnit 5 para poder usar el método assertEquals
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import com.caixaba.absis.microalumnos.entity.AlumnoEntity;
@ContextConfiguration(classes = {com.capgemini.microalumnos.Application.class})
@DataJpaTest
public class AlumnosRepositoryTest {
	@Autowired
	AlumnosRepository alumnosRepository;
	
	
	//Crea un test para el método findByCurso, que inserte un alumno con curso "Matematicas" y luego busque por ese curso y verifique que el alumno encontrado es el mismo que se insertó
	//Crea un test para el método findAllCursos, que inserte varios alumnos con cursos diferentes y luego busque todos los cursos y verifique que se han encontrado todos los cursos sin duplicados
	@Test
	void testFindByCurso() {
		AlumnoEntity alumno = new AlumnoEntity("12345678A", "Juan", "Perez", "Matematicas",9.0);
		alumnosRepository.save(alumno);
		assertEquals(alumno.getNombre(), alumnosRepository.findByCurso("Matematicas").get(0).getNombre());
	}
	@Test
	void testFindAllCursos() {
		AlumnoEntity alumno1 = new AlumnoEntity("12345678A", "Juan", "Perez", "Matematicas",9.0);
		AlumnoEntity alumno2 = new AlumnoEntity("87654321B", "Maria", "Gomez", "Fisica",8.5);
		AlumnoEntity alumno3 = new AlumnoEntity("11223344C", "Pedro", "Lopez", "Matematicas",7.5);
		alumnosRepository.save(alumno1);
		alumnosRepository.save(alumno2);
		alumnosRepository.save(alumno3);
		assertEquals(2, alumnosRepository.findAllCursos().size());
		assertTrue(alumnosRepository.findAllCursos().contains("Matematicas"));
		assertTrue(alumnosRepository.findAllCursos().contains("Fisica"));
	}
}
