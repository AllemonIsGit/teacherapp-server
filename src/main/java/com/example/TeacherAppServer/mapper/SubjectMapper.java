package com.example.TeacherAppServer.mapper;

import com.example.TeacherAppServer.domain.dto.request.CreateSubjectRequest;
import com.example.TeacherAppServer.domain.dto.response.StudentSubjectResponse;
import com.example.TeacherAppServer.domain.model.StudentSubject;
import com.example.TeacherAppServer.domain.model.TeachingSession;
import com.example.TeacherAppServer.domain.model.User;
import com.example.TeacherAppServer.repository.SubjectRepository;
import com.example.TeacherAppServer.service.AuthenticationService;
import com.example.TeacherAppServer.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SubjectMapper {
    private final SessionService sessionService;
    private final TeachingSessionMapper sessionMapper;
    private final SubjectRepository subjectRepository;


    public StudentSubject mapRequestToSubject(CreateSubjectRequest request, User loggedUser) {
        return StudentSubject.builder()
                .name(request.getName())
                .payPerSession(request.getPayPerSession())
                .user(loggedUser)
                .build();
    }

    public StudentSubjectResponse toStudentSubjectResponse(StudentSubject studentSubject) {
        return StudentSubjectResponse.builder()
                .id(studentSubject.getId())
                .name(studentSubject.getName())
                .payPerSession(studentSubject.getPayPerSession())
                .sessions(studentSubject.getSessions().stream().map(sessionMapper::toTeachingSessionResponse).toList())
                .build();
    }
}
