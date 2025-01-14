package org.wild.initializespring.initializespring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class InitializeSpring01Application {

	public static void main(String[] args) {
		SpringApplication.run(InitializeSpring01Application.class, args);
	}

	@GetMapping("/hello")
	public String sayHello() {
		return "Hello World!";
	}

}
