package com.myapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.myapp.properties.StorageProperties;
import com.myapp.security.services.StorageService;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
@EnableAutoConfiguration
public class MailingApplication {

	public static void main(String[] args) {
		SpringApplication.run(MailingApplication.class, args);
	}

	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			// storageService.deleteAll();
			storageService.init();
		};
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/**").allowedOrigins("http://localhost:4200")
						.allowedMethods("POST", "GET", "PATCH", "DELETE", "OPTIONS")
						.allowedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials",
								"Access-Control-Allow-Methods", "Access-Control-Allow-Headers", "Content-Type",
								"Authorization", "Cookie", "Access-Control-Request-Headers",
								"Access-Control-Request-Method")
						.allowCredentials(true);				
			}
		};
	}
}