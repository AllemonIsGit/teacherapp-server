package com.example.TeacherAppServer.service;

import com.example.TeacherAppServer.domain.dto.request.CreateSessionRequest;
import com.example.TeacherAppServer.domain.exception.SubjectNotFoundException;
import com.example.TeacherAppServer.domain.model.StudentSubject;
import com.example.TeacherAppServer.domain.model.TeachingSession;
import com.example.TeacherAppServer.domain.model.User;
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


    @Override
    public void save(CreateSessionRequest session) {
        StudentSubject subject = subjectRepository.findById(session.getSubjectId()).orElseThrow(() ->
                new SubjectNotFoundException("Subject not found."));

        sessionRepository.save(TeachingSession.builder()
                .price(subject.getPayPerSession())
                .subject(subject)
                .sessionDate(LocalDateTime.now())
                .build());
    }

    @Override
    public List<TeachingSession> getUserSessions(String username) {
        return null;
    }

    @Override
    public List<TeachingSession> getSubjectSessions(StudentSubject subject) {
        return sessionRepository.findAllBySubject(subject);
    }

    @Override
    public TeachingSession getSessionById(Integer id) {
        return null;
    }

    //TODO forgive me for what i just did
    @Override
    public List<TeachingSession> getAllByUser(User user) {
//        List<StudentSubject> subjects = subjectRepository.findAllByUser(user);
//
//        if (subjects.size() == 0) {
//            return null;
//        }
//
//        else if (subjects.size() == 1) {
//            return sessionRepository.findAllBySubject(subjects.get(0));
//        }
//
//        else {
//            List<TeachingSession> sessions = sessionRepository.findAllBySubject(subjects.get(0));
//            for (int i = 1; i < subjects.size(); i++) {
//                sessions.addAll(sessionRepository.findAllBySubject(subjects.get(i)));
//            }
//            return sessions;
//        }

        return subjectRepository.findAllByUser(user)
                .stream().flatMap(subject -> sessionRepository.findAllBySubject(subject).stream()).toList();
    }
}
