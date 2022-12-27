package com.example.TeacherAppServer.mapper;

import com.example.TeacherAppServer.domain.dto.request.CreateSubjectRequest;
import com.example.TeacherAppServer.domain.dto.response.StudentSubjectResponse;
import com.example.TeacherAppServer.domain.model.Subject;
import com.example.TeacherAppServer.domain.model.User;
import com.example.TeacherAppServer.repository.SubjectRepository;
import com.example.TeacherAppServer.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SubjectMapper {
    private final SessionService sessionService;
    private final SessionMapper sessionMapper;
    private final SubjectRepository subjectRepository;


    public Subject mapRequestToSubject(CreateSubjectRequest request, User loggedUser) {
        return Subject.builder()
                .name(request.getName())
                .payPerSession(request.getPayPerSession())
                .user(loggedUser)
                .build();
    }

    public StudentSubjectResponse toStudentSubjectResponse(Subject subject) {
        return StudentSubjectResponse.builder()
                .id(subject.getId())
                .name(subject.getName())
                .payPerSession(subject.getPayPerSession())
                .sessions(subject.getSessions().stream().map(sessionMapper::toTeachingSessionResponse).toList())
                .build();
    }
}
