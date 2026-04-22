package init.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entidad creada según skill
 */
@Entity
@Table(name = "recursos")
public class Recurso {

	@Id
	private String codigo;
	private String nombre;
	private String tipo;
	private LocalDateTime fecha_creacion;

	public Recurso() {
		super();
	}

	public Recurso(String codigo, String nombre, String tipo, LocalDateTime fecha_creacion) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.tipo = tipo;
		this.fecha_creacion = fecha_creacion;
	}

	public Recurso(String nombre, String tipo, LocalDateTime fecha_creacion) {
		super();
		this.nombre = nombre;
		this.tipo = tipo;
		this.fecha_creacion = fecha_creacion;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public LocalDateTime getFecha_creacion() {
		return fecha_creacion;
	}

	public void setFecha_creacion(LocalDateTime fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}
}
