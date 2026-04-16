package com.capgemini.micropersonas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//escanea los paquetes com.capgemini.micropersonas y com.caixaba.absis.micropersonas para encontrar los componentes de Spring

@SpringBootApplication(scanBasePackages = {"com.capgemini.micropersonas", "com.caixaba.absis.micropersonas"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
