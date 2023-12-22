package com.quizly.apigateway.apigateway.constants;

public class MicroServiceConstants {
    public static final String USERS_AUTH_MICROSERVICE = "/users/auth/**";
    public static final String USERS_MICORSERVICE = "users-service";
    public interface UsersMicroServiceConstants {
        String FETCH_BY_EMAIL = "/users/{email}";
    }
}
