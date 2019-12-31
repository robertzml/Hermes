package com.shengdangjia.hermesgateway;

import com.shengdangjia.hermesgateway.filter.IdTokenFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@EnableDiscoveryClient
@SpringBootApplication
public class HermesGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(HermesGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p
                        .order(1)
                        .path("/account-service/register/**")
                        .filters(
                                f -> f.stripPrefix(1)
                        )
                        .uri("lb://hermes-account")
                    )
                .route(p -> p
                        .order(2)
                        .path("/account-service/**")
                        .filters(
                                f -> f.stripPrefix(1)
                                        .filter(new IdTokenFilter())
                        )
                        .uri("lb://hermes-account"))
                .route(p -> p
                        .path("/authentication-service/**")
                        .filters(
                                f -> f.stripPrefix(1)
                        )
                        .uri("lb://hermes-authentication"))
                .build();
    }
}
