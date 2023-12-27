package com.quizly.users.controllers;

import com.quizly.users.dtos.UserDetailsDto;
import com.quizly.users.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{username}")
    public ResponseEntity<Optional<UserDetailsDto>>findByUsername(@PathVariable("username") String username){
        var user = userService.findByUsername(username.toLowerCase());
        return user.map(
                u->ResponseEntity.ok(Optional.of(UserDetailsDto.builder()
                .email(u.getEmail())
                .username(u.getUsername())
                .joinedAt(u.getCreatedAt())
                .build())))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/checkEmailAvailability")
    public ResponseEntity<Boolean> checkEmailAvailability(@RequestParam("email") String email){
        return ResponseEntity.ok(userService.findByEmail(email).isEmpty());
    }

    @GetMapping("/checkUsernameAvailability")
    public ResponseEntity<Boolean> checkUsernameAvailability(@RequestParam("username") String username){
        return ResponseEntity.ok(userService.findByUsername(username).isEmpty());
    }
}
