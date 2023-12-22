package com.quizly.apigateway.apigateway.configuration;

import com.quizly.apigateway.apigateway.constants.MicroServiceConstants;
import com.quizly.apigateway.apigateway.security.filters.JwtAuthorizationFilter;
import com.quizly.apigateway.apigateway.service.JwtService;
import com.quizly.apigateway.apigateway.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JwtService jwtService;
    private final AuthenticationConfiguration authenticationConfiguration;
    private final UserService userService;

    @Bean
    public AuthenticationManager usersAuthenticationManagerBean() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic(httpSecurityHttpBasicConfigurer -> httpSecurityHttpBasicConfigurer.disable())
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(MicroServiceConstants.USERS_AUTH_MICROSERVICE).permitAll()
                        .anyRequest().authenticated()

                )
                .addFilter(new JwtAuthorizationFilter(usersAuthenticationManagerBean(),jwtService,userService));

        return http.build();
    }
}
