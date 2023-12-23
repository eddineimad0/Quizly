package com.quizly.users.controllers;

import com.quizly.users.dtos.UserDetailsDto;
import com.quizly.users.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{email}")
    public ResponseEntity<Optional<UserDetailsDto>>findUserByEmail(@PathVariable("email") String userEmail){
        var user = userService.findByEmail(userEmail);
        return user.map(
                u->ResponseEntity.ok(Optional.of(UserDetailsDto.builder()
                .email(u.getEmail())
                .username(u.getUsername())
                .build())))
                .orElse(ResponseEntity.ok(null));
    }
}
