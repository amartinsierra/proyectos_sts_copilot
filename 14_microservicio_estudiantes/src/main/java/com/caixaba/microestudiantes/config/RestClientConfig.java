package com.caixaba.microestudiantes.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

	@Bean
	public RestClient restClient(
			RestClient.Builder builder,
			@Value("${microestudiantes.alumnos.base-url:http://localhost:8000}") String baseUrl) {
		return builder
				.baseUrl(baseUrl)
				.defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
				.build();
	}

	@Bean
	public RestClient.Builder restClientBuilder() {
		return RestClient.builder();
	}
}
