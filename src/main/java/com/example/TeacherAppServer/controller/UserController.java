package com.example.TeacherAppServer.controller;

import com.example.TeacherAppServer.domain.dto.response.UserDataResponse;
import com.example.TeacherAppServer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public UserDataResponse getUser() {
        return userService.getUserData();
    }
}

