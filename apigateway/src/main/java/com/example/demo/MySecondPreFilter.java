package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Mono;

@Component
@Order(2)
public class MySecondPreFilter implements GlobalFilter {
	
	private final Logger logger = LoggerFactory.getLogger(MySecondPreFilter.class);
	
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		logger.info("MySecondPreFilter :: filter () method execution starts...");
		// Accessing HTTP Request information
	    Mono<WebSession> session = exchange.getSession();
	    System.out.println("WEB SESSION : " + session.toString());
		logger.info("MySecondPreFilter :: filter () method execution ends...");
		return chain.filter(exchange);
	}
	public int getOrder() {
        return 2; // Must match the @Order annotation value
    }
}

