package com.example.berychc;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class BerychcApplication {

	public static void main(String[] args) {
		SpringApplication.run(BerychcApplication.class, args);
	}

}
