package com.quizly.apigateway.apigateway.service;

import com.quizly.apigateway.apigateway.dto.UserDto;
import com.quizly.apigateway.apigateway.feign.UsersServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UsersServiceClient usc;

    public Optional<UserDto> findByEmail(String email){
        return usc.fetchUserByEmail(email);
    }

}
