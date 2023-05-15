package com.example.TeacherAppServer.mapper;

import com.example.TeacherAppServer.domain.dto.request.CreateLessonRequest;
import com.example.TeacherAppServer.domain.model.Lesson;
import com.example.TeacherAppServer.service.UserContextService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Component
public class LessonMapper {
    private final UserContextService userContextService;

    public Lesson toLesson(CreateLessonRequest request) {
        return Lesson.builder()
                .date(request.getDate())
                .price(request.getPrice())
                .durationInMinutes((request.getDurationInMinutes()))
                .subjectId(request.getSubjectId())
                .done(false)
                .cancelled(false)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .createdBy(userContextService.getLoggedOnUser())
                .build();
    }
}
