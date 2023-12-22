package com.quizly.apigateway.apigateway.feign;

import com.quizly.apigateway.apigateway.constants.MicroServiceConstants;
import com.quizly.apigateway.apigateway.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = MicroServiceConstants.USERS_MICORSERVICE)
public interface UsersServiceClient {
    @GetMapping(value = MicroServiceConstants.UsersMicroServiceConstants.FETCH_BY_EMAIL)
    public Optional<UserDto> fetchUserByEmail(@PathVariable("email") String userEmail);
}
