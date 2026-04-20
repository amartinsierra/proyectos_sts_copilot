package com.capgemini.microalumnos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@EntityScan(basePackages = {

		"com.caixaba.absis.microalumnos.entity"
})
@EnableJpaRepositories(basePackages = {
		"com.caixaba.absis.microalumnos.repository"
})
@SpringBootApplication(
	scanBasePackages = {
		"com.capgemini.microalumnos.api",
		"com.caixaba.absis.microalumnos.service",
		
		"com.caixaba.absis.microalumnos.mapper"
	}
)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
