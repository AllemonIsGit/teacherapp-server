package com.example.TeacherAppServer.service;

import com.example.TeacherAppServer.domain.dto.request.CreateSessionRequest;
import com.example.TeacherAppServer.domain.dto.request.PatchSessionRequest;
import com.example.TeacherAppServer.domain.dto.response.SessionResponse;
import com.example.TeacherAppServer.domain.model.Subject;
import com.example.TeacherAppServer.domain.model.Session;
import com.example.TeacherAppServer.domain.model.User;

import java.util.List;

public interface SessionService {
    void save(CreateSessionRequest session);
    List<Session> getSubjectSessions(Subject subject);
    Session getSessionById(Integer id);

    List<Session> getAllByUser(User user);
    List<SessionResponse> getAllByUserAsResponse();
    void patchSession(Integer id, PatchSessionRequest patchSessionRequest);
}
