package com.quizly.apigateway.apigateway.security.filters;

import com.quizly.apigateway.apigateway.constants.SecurityConstants;
import com.quizly.apigateway.apigateway.dto.UserDto;
import com.quizly.apigateway.apigateway.service.JwtService;
import com.quizly.apigateway.apigateway.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.util.Optional;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    private final JwtService jwtService;
    private final UserService userService;
    private final SecurityConstants constants = new SecurityConstants();

    public JwtAuthorizationFilter(AuthenticationManager authManager, JwtService jwtService, UserService userService) {
        super(authManager);
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {

        String header = req.getHeader(constants.AUTHORIZATION_HEADER);
        if (header == null || !header.startsWith(constants.BEARER_PREFIX)) {
            // Authorization header not found continue along the responsibility chain.
            chain.doFilter(req, res);
            return;
        }

        UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(header);
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
        System.out.println(authenticationToken.toString());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(req, res);
    }

    // Reads the JWT from the Authorization header, and then uses JWT to validate the token
    private UsernamePasswordAuthenticationToken getAuthentication(String header) {
        String token = header.replace(constants.BEARER_PREFIX,"");
        if (jwtService.isTokenExpired(token)) {
            return null;
        }
        String email = jwtService.extractEmail(token);
        if(email == null) {
            return null;
        }
        Optional<UserDto> user = userService.findByEmail(email);
        return user.map(value -> new UsernamePasswordAuthenticationToken(
                        value.getEmail(),
                        "",
                        null))
                .orElse(null);
    }
}
