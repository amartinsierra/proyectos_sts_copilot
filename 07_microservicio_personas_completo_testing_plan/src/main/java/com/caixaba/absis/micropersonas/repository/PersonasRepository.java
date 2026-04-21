package com.caixaba.absis.micropersonas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import com.caixaba.absis.micropersonas.entity.PersonaEntity;



public interface PersonasRepository extends JpaRepository<PersonaEntity, Integer> {
	//método para buscar personas por rango de edad
	List<PersonaEntity> findByEdadBetween(int min, int max);
	//método para eliminar una persona por email, añade las anotaciones para métodos de acción
	@Modifying
	@Transactional
	void deleteByEmail(String email);
	
	
	
}
