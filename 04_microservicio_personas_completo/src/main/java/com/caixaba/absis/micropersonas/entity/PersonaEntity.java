package com.caixaba.absis.micropersonas.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "personas")
public class PersonaEntity {
	//atributos de la entidad: id, nombre, edad y email
	@Id
	private int id;
	private String nombre;
	private int edad;
	private String email;
	public PersonaEntity(int id, String nombre, int edad, String email) {
		this.id = id;
		this.nombre = nombre;
		this.edad = edad;
		this.email = email;
	}
	public PersonaEntity() {
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
