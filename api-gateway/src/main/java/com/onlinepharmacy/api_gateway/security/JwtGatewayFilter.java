package com.onlinepharmacy.api_gateway.security;

import lombok.RequiredArgsConstructor;
import org.apache.http.HttpHeaders;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class JwtGatewayFilter implements GlobalFilter {

    private final JwtService jwtService;

    @Override
    public Mono<Void> filter(
            ServerWebExchange exchange, GatewayFilterChain chain) {

        String path = exchange.getRequest()
                .getURI()
                .getPath();

        // Public paths — no JWT required
        if (isPublicPath(path)) {
            return chain.filter(exchange);
        }

        String authHeader = exchange.getRequest()
                .getHeaders()
                .getFirst(HttpHeaders.AUTHORIZATION);

        // JWT missing
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return unauthorized(exchange);
        }

        String token = authHeader.substring(7);

        // JWT invalid
        if (!jwtService.isTokenValid(token)) {
            return unauthorized(exchange);
        }

        // Extract role and enforce ADMIN-only routes
        String role = jwtService.extractRole(token);

        if (path.startsWith("/api/admin")) {
            if (!"ADMIN".equals(role)) {
                return forbidden(exchange);
            }
        }

        return chain.filter(exchange);
    }

    private boolean isPublicPath(String path) {
        return path.equals("/api/users/login")
                || path.equals("/api/users/signup")
                || path.equals("/api/medicine/allMedicine")
                || path.startsWith("/swagger-ui")
                || path.startsWith("/v3/api-docs")
                // Per-service Swagger paths routed through the gateway
                || path.startsWith("/user-service/swagger-ui")
                || path.startsWith("/user-service/v3/api-docs")
                || path.startsWith("/order-service/swagger-ui")
                || path.startsWith("/order-service/v3/api-docs")
                || path.startsWith("/admin-service/swagger-ui")
                || path.startsWith("/admin-service/v3/api-docs")
                || path.startsWith("/catalog-service/swagger-ui")
                || path.startsWith("/catalog-service/v3/api-docs")
                || path.startsWith("/actuator");
    }

    private Mono<Void> unauthorized(ServerWebExchange exchange) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }

    private Mono<Void> forbidden(ServerWebExchange exchange) {
        exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
        return exchange.getResponse().setComplete();
    }
}
