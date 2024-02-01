package com.example.demo;

import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

import org.slf4j.Logger;

@Component
@Order(3)
public class PostFilter implements GlobalFilter{

	private final Logger logger = LoggerFactory.getLogger(PostFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        logger.info("MyPostFilter :: filter () method execution starts...");

        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            // Executed after the response has been sent to the client
            ServerHttpResponse response = exchange.getResponse();
            ServerHttpRequest request = exchange.getRequest();

            logger.info("Response Status Code: {}", response.getStatusCode());
            logger.info("Request URI: {}", request.getURI());
            logger.info("MyPostFilter :: filter () method execution ends...");
            // Additional post-processing tasks can be performed here
        }));
    }
    public int getOrder() {
        return 3; // Must match the @Order annotation value
    }
}
