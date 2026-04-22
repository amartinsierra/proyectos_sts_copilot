package com.caixaba.microtypi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

	@Bean
	RestClient typiRestClient(RestClient.Builder builder) {
		return builder.baseUrl("https://jsonplaceholder.typicode.com").build();
	}
}
