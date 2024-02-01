package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetRest {
	
	@Autowired
	private WelcomeApiClient welcomeClient;
	
	@GetMapping("/greet")
	public String getGreetMsg() {
		
		String welcomeMsg = welcomeClient.invokeWelcomeMsg();
		
		String greetMsg = "Good Morning, ";
		
		return greetMsg.concat(welcomeMsg);
	}

}
