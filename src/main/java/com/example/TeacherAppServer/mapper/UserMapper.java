package com.example.TeacherAppServer.mapper;

import com.example.TeacherAppServer.domain.dto.request.CreateUserRequest;
import com.example.TeacherAppServer.domain.dto.response.UserDataResponse;
import com.example.TeacherAppServer.domain.model.Subject;
import com.example.TeacherAppServer.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final PasswordEncoder passwordEncoder;
    private final SubjectMapper subjectMapper;

    public User mapRequestToUser(CreateUserRequest createUserRequest) {
        return User.builder()
                .username(createUserRequest.getUsername())
                .dateCreated(LocalDateTime.now())
                .nickname(createUserRequest.getNickname())
                .password(passwordEncoder.encode(createUserRequest.getPassword()))
                .build();
    }

    public UserDataResponse mapUserToResponseData(User user, List<Subject> subjects) {
        return UserDataResponse.builder()
                .nickname(user.getNickname())
                .subjects(subjects.stream().map(subjectMapper::toSubjectResponse).toList())
                .build();
    }
}
