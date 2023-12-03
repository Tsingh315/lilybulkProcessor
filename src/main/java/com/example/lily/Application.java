package com.example.lily;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;

@SpringBootApplication
@Configuration
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
