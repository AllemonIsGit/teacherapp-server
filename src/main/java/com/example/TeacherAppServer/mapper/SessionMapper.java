package com.example.TeacherAppServer.mapper;


import com.example.TeacherAppServer.domain.dto.response.SessionResponse;
import com.example.TeacherAppServer.domain.model.Session;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SessionMapper {

    public SessionResponse toSessionResponse(Session session) {
        return SessionResponse.builder()
                .sessionId(session.getSessionId())
                .price(session.getPrice())
                .subjectId(session.getSubject().getId())
                .date(session.getSessionDate())
                .build();
    }

    public List<SessionResponse> toSessionResponseList(List<Session> sessions) {
        List<SessionResponse> responseList = new ArrayList<>();

        for (Session session: sessions) {

            responseList.add(toSessionResponse(session));
        }
        return responseList;
    }
}
