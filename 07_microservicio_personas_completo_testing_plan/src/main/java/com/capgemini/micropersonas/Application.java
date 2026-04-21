package com.capgemini.micropersonas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//escanea los paquetes com.capgemini.micropersonas y com.caixaba.absis.micropersonas para encontrar los componentes de Spring

@SpringBootApplication(scanBasePackages = {"com.capgemini.micropersonas", "com.caixaba.absis.micropersonas"})
@EnableJpaRepositories(basePackages = "com.caixaba.absis.micropersonas.repository")
@EntityScan(basePackages = "com.caixaba.absis.micropersonas.entity")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
