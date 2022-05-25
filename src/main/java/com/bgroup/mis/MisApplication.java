package com.bgroup.mis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController

public class MisApplication {

	public static void main(String[] args) {
		SpringApplication.run(MisApplication.class, args);
	}
	
	@GetMapping
	public static String helloWorld() {
		return "Welcome to Springboot!";
	}
	
	@GetMapping("api/routes/1")
	public static String firstRoute() {
		return "This is the first route!";
	}

}
