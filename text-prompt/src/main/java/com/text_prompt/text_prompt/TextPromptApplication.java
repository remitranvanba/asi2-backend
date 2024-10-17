package com.text_prompt.text_prompt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@EnableJms
@SpringBootApplication
public class TextPromptApplication {

	public static void main(String[] args) {
		SpringApplication.run(TextPromptApplication.class, args);
	}

}
