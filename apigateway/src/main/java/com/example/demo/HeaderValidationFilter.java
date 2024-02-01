package com.example.demo;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
@Order(0)
public class HeaderValidationFilter implements GlobalFilter{

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    	System.out.println("HeaderValidationFilter execution starts......");
        // Get the value of a specific header
        String headerValue = exchange.getRequest().getHeaders().getFirst("X-Custom-Header");

        // Perform header validation logic
        if (isValidHeaderValue(headerValue)) {
            // Header is valid, continue with the filter chain
        	System.out.println("HeaderValidationFilter execution starts......");
            return chain.filter(exchange);
        } else {
            // Header is not valid, return a custom response
        	System.out.println("HeaderValidationFilter execution starts......");
            return createErrorResponse(exchange);
        }
    }

    private boolean isValidHeaderValue(String value) {
        // Implement your header validation logic here
        // For example, check if the header value meets certain criteria
        return value != null && value.equals("1234");
    }

    private Mono<Void> createErrorResponse(ServerWebExchange exchange) {
        // Customize the response based on your requirements
        exchange.getResponse().getHeaders().set("Content-Type", "application/json");
        exchange.getResponse().setStatusCode(org.springframework.http.HttpStatus.UNAUTHORIZED);
        String responseBody = "{\"error\": \"" + "X-Custom-Header is invalid" + "\"}";
        return exchange.getResponse().writeWith(Mono.just(exchange.getResponse().bufferFactory().wrap(responseBody.getBytes())));
    }
    
    public int getOrder() {
        // Set the order for the filter
        return 0;
    }
}

