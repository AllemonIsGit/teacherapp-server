package com.example.TeacherAppServer.service;

import com.example.TeacherAppServer.domain.dto.request.CreateUserRequest;
import com.example.TeacherAppServer.domain.exception.*;
import com.example.TeacherAppServer.domain.model.User;
import com.example.TeacherAppServer.mapper.UserMapper;
import com.example.TeacherAppServer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public AuthenticationService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public User getLoggedUser() {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found."));
    }


    public void register(CreateUserRequest createUserRequest) {
        validate(createUserRequest);
        userRepository.save(userMapper.mapRequestToUser(createUserRequest));

    }

    private void validate(CreateUserRequest createUserRequest) {

        if (!isUserRequestValid(createUserRequest)) {
            throw new InvalidCreateUserRequestException("Invalid request.");
        }
        if (!doesPasswordMatch(createUserRequest.getPassword(), createUserRequest.getRePassword())) {
            throw new PasswordsDoesNotMatchException("Passwords does not match.");
        }
        if (doesUsernameExist(createUserRequest.getUsername())) {
            throw new UsernameTakenException("Username is already registered.");
        }
        if (createUserRequest.getNickname() == null || Objects.equals(createUserRequest.getNickname(), "")) {
            throw new NicknameNotProvidedException("Nickname has to bo provided");
        }
    }

    private Boolean isUserRequestValid(CreateUserRequest createUserRequest) {
        return (createUserRequest.getUsername() != null &&
                createUserRequest.getPassword() != null &&
                createUserRequest.getRePassword() != null);
    }

    private Boolean doesPasswordMatch(String password, String rePassword) {
        return Objects.equals(password, rePassword);
    }

    private Boolean doesUsernameExist(String username) {
        return userRepository.existsByUsername(username);
    }
}