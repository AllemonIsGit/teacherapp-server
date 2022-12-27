package com.example.TeacherAppServer.service;

import com.example.TeacherAppServer.domain.dto.response.UserDataResponse;
import com.example.TeacherAppServer.domain.model.Subject;
import com.example.TeacherAppServer.domain.model.User;
import com.example.TeacherAppServer.mapper.UserMapper;
import com.example.TeacherAppServer.repository.SubjectRepository;
import com.example.TeacherAppServer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDaoService implements UserService {

    private final UserRepository userRepository;
    private final AuthenticationService authenticationService;
    private final SubjectRepository subjectRepository;
    private final UserMapper userMapper;
    private final SessionService sessionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("User not found in Database."));
    }

    public UserDataResponse getUserData() {
        User user = authenticationService.getLoggedUser();
        List<Subject> subjects = subjectRepository.findAllByUser(user);
        return userMapper.mapUserToResponseData(user, subjects);
    }
}
