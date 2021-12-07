package com.example.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {
	
//	@Bean
//	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
//		return builder.routes()
//				.route(p -> p.path("/student-service/**")
//						.uri("lb://student-service/"))
//				.route(p -> p.path("/course-service/**")
//						.uri("lb://course-service"))
//				.route(p -> p.path("/student-course-service/**")
//						.uri("lb://student-course-service"))
//				.route(p -> p.path("/department-service/**")
//						.uri("lb://department-service"))
//				.route(p -> p.path("/faculty-service/**")
//						.uri("lb://faculty-service"))
//				.build();		
//	}
}
