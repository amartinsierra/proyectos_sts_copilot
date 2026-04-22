---
description: Agrega al pom.xml las dependencias necesarias para utilizar mapstruct
tools:
  - type: filesystem
    permissions:
      - write
      - create
      - read: pom.xml
      - write: pom.xml
  - maven
---
	Tu tarea es agregar el las siguientes entradas en el pom.xml:
  
        
    1. Dependencia de mapstruct. Debes añadir exactamente esta dependencia, en la sección <dependencies>
 
    <dependency>
            <groupId>org.mapstruct</groupId>
            <artifactId>mapstruct</artifactId>
            <version>1.5.5.Final</version>
   </dependency>
	
			
	2.Processor de mapstruct. Debes añadir exactamente esta entrada en la sección de plugins:
	
		<plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.8.1</version>
                <configuration>
                    <annotationProcessorPaths>
                        <path>
                            <groupId>org.mapstruct</groupId>
                            <artifactId>mapstruct-processor</artifactId>
                            <version>1.5.5.Final</version>
                        </path>
                    </annotationProcessorPaths>
                </configuration>
            </plugin>
		
	3. Si alguno de los elementos ya existe en el pom.xml, no lo añadas de nuevo, solo asegúrate de que la versión es la correcta. Si la versión no es la correcta, actualízala a la versión indicada.
	4. No elimines ni modifiques nada más en el pom.xml, solo añade lo que se te ha indicado.
	5. No generes código java automáticamente 
	

