package com.example.TeacherAppServer.mapper;

import com.example.TeacherAppServer.domain.dto.request.CreateSubjectRequest;
import com.example.TeacherAppServer.domain.dto.response.SubjectResponse;
import com.example.TeacherAppServer.domain.model.Subject;
import com.example.TeacherAppServer.domain.model.User;
import com.example.TeacherAppServer.repository.SubjectRepository;
import com.example.TeacherAppServer.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SubjectMapper {
    private final SessionMapper sessionMapper;

    public Subject toSubject(CreateSubjectRequest request, User loggedUser) {
        return Subject.builder()
                .name(request.getName())
                .payPerSession(request.getPayPerSession())
                .user(loggedUser)
                .build();
    }

    public SubjectResponse toSubjectResponse(Subject subject) {
        return SubjectResponse.builder()
                .id(subject.getId())
                .name(subject.getName())
                .payPerSession(subject.getPayPerSession())
                .sessions(subject.getSessions().stream().map(sessionMapper::toSessionResponse).toList())
                .build();
    }

    public List<SubjectResponse> toSubjectResponseList(List<Subject> subjects) {
        List<SubjectResponse> responseList = new ArrayList<>();

        for (Subject subject: subjects) {
            responseList.add(toSubjectResponse(subject));
        }

        return responseList;
    }
}
