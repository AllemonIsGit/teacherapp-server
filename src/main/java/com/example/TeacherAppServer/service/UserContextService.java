package com.example.TeacherAppServer.service;

import com.example.TeacherAppServer.domain.model.Lesson;
import com.example.TeacherAppServer.domain.model.Session;
import com.example.TeacherAppServer.domain.model.Subject;
import com.example.TeacherAppServer.domain.model.User;
import com.example.TeacherAppServer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserContextService {
    private final UserRepository userRepository;

    public User getLoggedOnUser() {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findUserByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("Username not found.")
        );
    }

    public Boolean isOwnerOfSubject(Subject subject) {
        return subject.getUser() == getLoggedOnUser();
    }

    public Boolean isOwnerOfSession(Session session) {
        return session.getSubject().getUser() == getLoggedOnUser();
    }

    public Boolean isOwnerOfLesson(Lesson lesson) {
        return lesson.getUser() == getLoggedOnUser();
    }
}
