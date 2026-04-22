name: skill-remote-rest
# Skill: Cliente rest
**Description:** Define como crear un cliente que accede a un servicio externo mediante RestClient. 
---
## Instrucciones
- Debes utilizar la interfaz RestClient de Spring 6
- El objeto RestClient será creado en una clase de configuración
e inyectado por constructor donde se vaya a utilizar
- Cuando se realizan peticiones post, indica siempre la cabecera
contentType para especificar que utilizas tipo JSON
- No inventes métodos, RestClient no es WebClient
- En caso de devolver una lista JSON, trasformar a array de objetos Java


## Ejemplo peticion GET
varRestClient.get()
.uri(url)
.retrieve()
.body(Tipo[].class)

## Ejemplo peticion POST
varRestClient.post()
.uri(url)
.contentType(MediaType.APPLICATION_JSON)
.body(objeto)
.retrieve()