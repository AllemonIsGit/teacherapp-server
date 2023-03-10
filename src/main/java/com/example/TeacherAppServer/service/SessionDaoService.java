package com.example.TeacherAppServer.service;

import com.example.TeacherAppServer.domain.dto.request.CreateSessionRequest;
import com.example.TeacherAppServer.domain.dto.request.PatchSessionRequest;
import com.example.TeacherAppServer.domain.dto.response.SessionResponse;
import com.example.TeacherAppServer.domain.exception.AccessForbiddenException;
import com.example.TeacherAppServer.domain.exception.SessionNotFoundException;
import com.example.TeacherAppServer.domain.exception.SubjectNotFoundException;
import com.example.TeacherAppServer.domain.model.Session;
import com.example.TeacherAppServer.domain.model.Subject;
import com.example.TeacherAppServer.domain.model.User;
import com.example.TeacherAppServer.mapper.SessionMapper;
import com.example.TeacherAppServer.repository.SessionRepository;
import com.example.TeacherAppServer.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SessionDaoService implements SessionService {
    private final SessionRepository sessionRepository;
    private final SubjectRepository subjectRepository;
    private final UserContextService userContextService;
    private final SessionMapper sessionMapper;


    @Override
    public void save(CreateSessionRequest session) {
        Subject subject = subjectRepository.findById(session.getSubjectId()).orElseThrow(() ->
                new SubjectNotFoundException("Subject not found."));

        sessionRepository.save(Session.builder()
                .price(subject.getPayPerSession())
                .subject(subject)
                .sessionDate(LocalDateTime.now())
                .build());
    }

    @Override
    public List<Session> getSubjectSessions(Subject subject) {
        return sessionRepository.findAllBySubject(subject);
    }

    @Override
    public Session getSessionById(Integer id) {
        return null;
    }

    @Override
    public List<Session> getAllByUser(User user) {
        return subjectRepository.findAllByUser(user)
                .stream().flatMap(subject -> sessionRepository.findAllBySubject(subject).stream()).toList();
    }

    public List<SessionResponse> getAllByUserAsResponse() {
        List<Session> sessions  = subjectRepository.findAllByUser(userContextService.getLoggedOnUser())
                .stream().flatMap(subject -> sessionRepository.findAllBySubject(subject).stream()).toList();
        return sessionMapper.toSessionResponseList(sessions);
    }

    @Override
    public void patchSession(Integer id, PatchSessionRequest patchSessionRequest) {
        Session newSession = sessionRepository.findById(id)
                .orElseThrow(() -> new SessionNotFoundException("Session not found."));
        if (!userContextService.isOwnerOfSession(newSession)) {
            throw new AccessForbiddenException("Forbidden.");
        }

        if (patchSessionRequest.getPrice() != null) {
            newSession.setPrice(patchSessionRequest.getPrice());
        }

        sessionRepository.save(newSession);
    }
}
