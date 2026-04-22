package com.capgemini.microtypi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
		scanBasePackages = {
				"com.caixaba.microtypi.service",
				"com.capgemini.microtypi.web",
				"com.caixaba.microtypi.config"
		}
)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
