package com.caixaba.absis.microalumnos.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

// Esta clase representa la entidad Alumno, que se corresponde con la tabla "alumnos" en la base de datos.
@Entity
@Table(name = "alumnos")
public class AlumnoEntity {
	//define los campos de la entidad: dni, nombre, email, curso y nota
	private String dni;
	private String nombre;
	private String email;
	private String curso;
	private Double nota;
	//constructor vacío
	public AlumnoEntity() {
	}
	//constructor con parámetros
	public AlumnoEntity(String dni, String nombre, String email, String curso, Double nota) {
		this.dni = dni;
		this.nombre = nombre;
		this.email = email;
		this.curso = curso;
		this.nota = nota;
	}
	//getters y setters para cada campo
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public Double getNota() {
		return nota;
	}
	public void setNota(Double nota) {
		this.nota = nota;
	}
	
}
