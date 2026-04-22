---
description: Agrega el plugin open-api-generator-maven-plugin (versión 7.4.0),
las dependencias necesarias y el archivo de configuración YAML 
cuyo nombre se pasa como parámetro (yamlName), dentro del proyecto indicado (proyectName).
El YAML se ubica en la carpeta src/main/resources/openapi.
tools:
  - type: filesystem
    permissions:
      - write
      - create
      - read: src/main/resources/openapi/{{yamlName}}.yaml
      - write: src/main/resources/openapi/{{yamlName}}.yaml
      - read: pom.xml
      - write: pom.xml
  - maven
---
	Tu tarea es agregar el plugin open-api-generator-maven-plugin al proyecto indicado en el parametro {{proyectName}} siguiendo estas reglas:
  
    PARÁMETRO OBLIGATORIO:
      - Las acciones se realizarán en el proyecto {{proyectName}}
      - El usuario debe proporcionar el nombre del archivo YAML (yamlName) que se encuentra en src/main/resources/openapi.
      - El fichero se encontrará en: src/main/resources/openapi/<yamlName>.yaml
      
      
    1. Añade al pom.xml estas dependencias EXACTAS:
 
    <!-- org.openapitools.jackson.nullable -->
		<dependency>
			<groupId>org.openapitools</groupId>
			<artifactId>jackson-databind-nullable</artifactId>
			<version>0.2.6</version>
		</dependency>
	
			
	2. Añade al pom.xml el plugin EXACTO:
	
		<plugin>
			<groupId>org.openapitools</groupId>
			<artifactId>openapi-generator-maven-plugin</artifactId>
			<version>7.4.0</version>
			<executions>
				<execution>
					<id>generate-personas-api</id>
					<phase>generate-sources</phase>
					<goals>
						<goal>generate</goal>
					</goals>
					<configuration>
						<inputSpec>
							${project.basedir}/src/main/resources/openapi/{{yamlName}}.yaml</inputSpec>
						<generatorName>spring</generatorName>
						<output>
							${project.build.directory}/generated-sources/openapi</output>
						<apiPackage>com.capgemini.{{paquete}}.api</apiPackage>
						<modelPackage>
							com.capgemini.{{paquete}}.api.domain</modelPackage>
						<configOptions>
							<interfaceOnly>true</interfaceOnly>
							<skipDefaultInterface>true</skipDefaultInterface>
							<useTags>true</useTags>
							<useSpringBoot3>true</useSpringBoot3>
							<generateApiTests>false</generateApiTests>
							<generateModelTests>false</generateModelTests>
						</configOptions>
					</configuration>
				</execution>
			</executions>
		</plugin>
		
		Donde {{yamlName}} es el nombre del archivo YAML que se pasa como parámetro y 
		{{paquete}} es el nombre del paquete que se pasa como parámetro.
		
	
	3. No elimines ni modifiques nada más en el pom.xml, solo añade lo que se te ha indicado.
	4. Tampoco modifiques el archivo {{yamlName}}.yaml
	5. No generes código java automáticamente, salvo el que crea el propio plugin, solo añade el plugin y las dependencias al pom.xml.
	6. Si el archivo {{yalName}}.yaml no existe, no hagas nada e informa al usuario 
	

