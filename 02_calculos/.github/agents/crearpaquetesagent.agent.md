---
description: Este agente se encarga de crear los paquetes necesarios para el proyecto.
Con la separación en capgemini (api/web/config) y caixaba (service/repository/entity/mapper): ''
tools: ['java_debugger', 'run_in_terminal', 'apply_patch', 'manage_todo_list', 'get_errors', 'run_subagent', 'replace_string_in_file', 'create_file', 'get_terminal_output', 'list_dir', 'file_search', 'grep_search', 'validate_cves', 'insert_edit_into_file', 'read_file']
---
1. Sobre el proyecto cuyo nombre se recibe en el parámetro {{proyectName}}, crea los paquetes necesarios para el proyecto en estos directorios: src/main/java:
    
    - com.capgemini.{{paquete}}.config
    - com.capgemini.{{paquete}}.web
    
    - com.caixaba.{{paquete}}.service
    - com.caixaba.{{paquete}}.repository
    - com.caixaba.{{paquete}}.entity
    - com.caixaba.{{paquete}}.mapper
    
    Reglas:
    - Crea todos los directorios si no existen.
    - No borres ningún archivo existente ni modifiques.
    - No generes clases java salvo que el usuario lo solicite explícitamente.
    - No añadas dependencias ni modifiques el pom.xml.
    - Si un directorio ya existe, no hagas nada.
