package com.quizly.users.dtos;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsDto {
    @JsonProperty("username")
    private String username;
    @JsonProperty("email")
    private String email;
}
