package com.example.TeacherAppServer.domain.dto.response;

import lombok.Data;

@Data
public class AuthenticationResponse {
    private final String token;
}
