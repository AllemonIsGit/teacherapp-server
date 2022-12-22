package com.example.TeacherAppServer.mapper;


import com.example.TeacherAppServer.domain.dto.response.TeachingSessionResponse;
import com.example.TeacherAppServer.domain.model.TeachingSession;
import org.springframework.stereotype.Component;

@Component
public class TeachingSessionMapper {

    public TeachingSessionResponse toTeachingSessionResponse(TeachingSession session) {
        return TeachingSessionResponse.builder()
                .sessionId(session.getSessionId())
                .price(session.getPrice())
                .subjectId(session.getSubject().getId())
                .build();
    }
}
