package com.example.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

import java.util.Map;

@Component
public class JwtAuthenticationFilter implements GlobalFilter {

    private final WebClient.Builder webClientBuilder;

    @Autowired
    public JwtAuthenticationFilter(WebClient.Builder builder) {
        this.webClientBuilder = builder;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange,
                             GatewayFilterChain chain) {

        String path =
                exchange.getRequest().getURI().getPath();

        if (path.contains("/auth/")) {
            return chain.filter(exchange);
        }

        if (!exchange.getRequest()
                .getHeaders()
                .containsKey("Authorization")) {

            exchange.getResponse()
                    .setStatusCode(HttpStatus.UNAUTHORIZED);

            return exchange.getResponse().setComplete();
        }

        String token =
                exchange.getRequest()
                        .getHeaders()
                        .getFirst("Authorization")
                        .replace("Bearer ", "");

        return webClientBuilder.build()
                .get()
                .uri("http://localhost:5001/auth/validate-token?token=" + token)
                .retrieve()
                .bodyToMono(Map.class)
                .flatMap(response -> {

                    String role =
                            response.get("role").toString();

                    if (exchange.getRequest().getMethod()
                            == HttpMethod.DELETE &&
                            !role.equals("ADMIN")) {

                        exchange.getResponse()
                                .setStatusCode(HttpStatus.FORBIDDEN);

                        return exchange.getResponse().setComplete();
                    }

                    return chain.filter(exchange);
                });
    }
}
