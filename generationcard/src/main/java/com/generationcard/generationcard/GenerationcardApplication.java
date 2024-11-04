package com.generationcard.generationcard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class GenerationcardApplication {

	public static void main(String[] args) {
		SpringApplication.run(GenerationcardApplication.class, args);
	}

}
