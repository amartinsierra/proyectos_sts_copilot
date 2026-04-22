---
name: skill-entity
# Skill: JPA Entity Standard
**Description:** Define entidades JPA siguiendo las reglas de nombrado de la base de datos.
---
## Instrucciones
- Usa la anotación `@Entity` de JPA
- Usa la anotación `@Table(name = "nombre_en_plural")` con nombres en minúsculas y guiones bajos (snake_case).
- Añade la anotación `@Id` al primer campo de la entidad
- Incluye siempre los siguientes constructores:
- Constructor sin parámetros
- Constructor con todos los parámetros
- Constructor con todos los parámetros menos la clave primaria
- Añade setter y getter para cada uno de los campos. A excepción de la primary
que solo tendrá getter
- Añade siempre un comentario al principio que diga `Entidad creada según skill`

## Ejemplo
```java
@Entity
@Table(name = "users_profile")
public class UserProfile {
    @Id
    private Long id;
	private String name;
	public UserProfile(){}
	public UserProfile(Long id, String name){
		this.id=id;
		this.name=name;
	}
	public UserProfile(String name){
		this.name=name;
	}
	//getters y setters
}
