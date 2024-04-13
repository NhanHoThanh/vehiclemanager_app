package com.example.demo.configuration;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import io.jsonwebtoken.Claims;
import reactor.core.publisher.Mono;
import com.example.demo.models.ReponseObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RefreshScope
@Component
public class AuthenticationFilter implements GatewayFilter {
    @Autowired
    private RouterValidator routerValidator;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        if (routerValidator.isSecured.test(request)) {
            if (this.isAuthMissing(request))
                return this.onError(exchange, "Authorization header is missing in request", HttpStatus.UNAUTHORIZED);

            final String token = this.getAuthHeader(request);

            if (jwtUtil.isTokenInvalid(token))
                return this.onError(exchange, "Authorization header is invalid", HttpStatus.UNAUTHORIZED);

            this.populateRequestWithHeaders(exchange, token);
        }
        return chain.filter(exchange);
    }

    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        ReponseObject responseObject = new ReponseObject(false, err);
        DataBuffer buffer;
        try {
            buffer = exchange.getResponse().bufferFactory().wrap(new ObjectMapper().writeValueAsBytes(responseObject));
            return exchange.getResponse().writeWith(Mono.just(buffer));
        } catch (JsonProcessingException e) {
            return Mono.error(e);
        }
    }

    private String getAuthHeader(ServerHttpRequest request) {
        HttpHeaders headers = request.getHeaders();
        if (headers.containsKey("Authorization")) {
            List<String> authHeaders = headers.get("Authorization");
            return authHeaders.get(0);
        }
        return null;
    }

    private boolean isAuthMissing(ServerHttpRequest request) {
        return !request.getHeaders().containsKey("Authorization");
    }

    private void populateRequestWithHeaders(ServerWebExchange exchange, String token) {
        Claims claims = jwtUtil.getAllClaimsFromToken(token);
        ServerHttpRequest mutatedRequest = exchange.getRequest().mutate()
        .headers(httpHeaders -> {
            httpHeaders.set("role", String.valueOf(claims.get("role")));
        })
        .build();
        exchange.mutate().request(mutatedRequest).build();
    }
}
