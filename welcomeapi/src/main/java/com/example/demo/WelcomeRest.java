package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeRest {

	@Autowired
	private Environment environment;
	
	@GetMapping("/welcome")
	public String welcomeMessage() {
		String message = "Welcome to Ashok IT(" + environment.getProperty("server.port")+")";
		return message;
	}
}
