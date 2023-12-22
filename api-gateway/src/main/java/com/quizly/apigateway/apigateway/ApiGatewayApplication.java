package com.quizly.apigateway.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ApiGatewayApplication {

//	@Bean
//	public RouteLocator customRouteLocator(RouteLocatorBuilder builder){
//		return builder.routes()
//				.route(
//						"auth",
//						r->r.path("/auth/**")
//						.filters(f-> f.stripPrefix(1))
//						.uri("lb://auth")
//				)
//				.build();
//	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource(){
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**",config);
//		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean();
//		bean.setOrder(0);
		return source;

	}

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

}
