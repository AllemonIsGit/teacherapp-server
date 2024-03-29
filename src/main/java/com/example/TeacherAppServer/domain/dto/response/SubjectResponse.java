package com.example.TeacherAppServer.domain.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SubjectResponse {
    private Integer id;
    private String name;
    private Integer payPerSession;
    private List<SessionResponse> sessions;
    private List<LessonResponse> lessons;
}
