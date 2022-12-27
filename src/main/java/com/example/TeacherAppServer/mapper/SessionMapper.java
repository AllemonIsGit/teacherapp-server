package com.example.TeacherAppServer.mapper;


import com.example.TeacherAppServer.domain.dto.response.TeachingSessionResponse;
import com.example.TeacherAppServer.domain.model.Session;
import org.springframework.stereotype.Component;

@Component
public class SessionMapper {

    public TeachingSessionResponse toTeachingSessionResponse(Session session) {
        return TeachingSessionResponse.builder()
                .sessionId(session.getSessionId())
                .price(session.getPrice())
                .subjectId(session.getSubject().getId())
                .build();
    }
}
