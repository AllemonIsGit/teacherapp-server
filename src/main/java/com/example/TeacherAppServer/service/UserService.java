package com.example.TeacherAppServer.service;

import com.example.TeacherAppServer.domain.dto.response.UserDataResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDataResponse getUserData();
}
