package com.example.TeacherAppServer.controller;

import com.example.TeacherAppServer.domain.dto.request.CreateUserRequest;
import com.example.TeacherAppServer.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("api/v1/authentication")
public class AuthenticationController {
    private static final String SUCCESSFUL_REGISTRATION_MESSAGE = "Account created.";
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<String> createUser(@RequestBody @Valid CreateUserRequest createUserRequest) {
        authenticationService.register(createUserRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(SUCCESSFUL_REGISTRATION_MESSAGE);
    }
}
