package com.example.demo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@Configuration
@EnableHystrix
public class GatewayConfiguration {

    @Autowired
    private AuthenticationFilter filter;

    @Bean
        public RouteLocator routerBuilder(RouteLocatorBuilder routeLocatorBuilder){ 
                return routeLocatorBuilder.routes() 
                        .route("demo",r->r.path("/demo/**") 
                            .filters(f -> f.filter(filter))
                            .uri("lb://demo"))
                        .route("auth-service", r -> r.path("/api/v1/auth/**")
                            .uri("lb://auth-service"))
                        .build();
        }
}
