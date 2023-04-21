package com.example.TeacherAppServer.mapper;

import com.example.TeacherAppServer.domain.dto.request.CreateUserRequest;
import com.example.TeacherAppServer.domain.model.User;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.*;

@AllArgsConstructor
class UserMapperTest {
    @Test
    void mapRequestToUserTest() {
        //Given
        SubjectMapper subjectMapper = new SubjectMapper(null);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        CreateUserRequest createUserRequest = new CreateUserRequest("Username", "Password", "Password", "Nickname");
        UserMapper userMapper = new UserMapper(passwordEncoder, subjectMapper);
        //When
        User expectedUser = userMapper.mapRequestToUser(createUserRequest);
        //Then
        then(expectedUser.getUsername()).isEqualTo("Username");
        then(expectedUser.getNickname()).isEqualTo("Nickname");
    }
}