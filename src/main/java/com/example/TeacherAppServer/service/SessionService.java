package com.example.TeacherAppServer.service;

import com.example.TeacherAppServer.domain.dto.request.CreateSessionRequest;
import com.example.TeacherAppServer.domain.model.StudentSubject;
import com.example.TeacherAppServer.domain.model.TeachingSession;
import com.example.TeacherAppServer.domain.model.User;

import java.util.List;

public interface SessionService {
    void save(CreateSessionRequest session);
    List<TeachingSession> getUserSessions(String username);
    List<TeachingSession> getSubjectSessions(StudentSubject subject);
    TeachingSession getSessionById(Integer id);

    List<TeachingSession> getAllByUser(User user);
}
