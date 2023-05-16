package com.example.TeacherAppServer.mapper;

import com.example.TeacherAppServer.domain.dto.request.CreateLessonRequest;
import com.example.TeacherAppServer.domain.dto.response.LessonResponse;
import com.example.TeacherAppServer.domain.exception.SubjectNotFoundException;
import com.example.TeacherAppServer.domain.model.Lesson;
import com.example.TeacherAppServer.repository.SubjectRepository;
import com.example.TeacherAppServer.service.UserContextService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Component
public class LessonMapper {
    private final UserContextService userContextService;
    private final SubjectRepository subjectRepository;

    public LessonResponse toLessonResponse(Lesson lesson) {
        return LessonResponse.builder()
                .id(lesson.getId())
                .date(lesson.getDate())
                .price(lesson.getPrice())
                .durationInMinutes(lesson.getDurationInMinutes())
                .createdAt(lesson.getCreatedAt())
                .updatedAt(lesson.getUpdatedAt())
                .done(lesson.isDone())
                .cancelled(lesson.isCancelled())
                .subjectId(lesson.getSubject().getId())
                .build();
    }
    public Lesson toLesson(CreateLessonRequest request) {
        LocalDateTime timeNow = LocalDateTime.now();
        return Lesson.builder()
                .date(request.getDate())
                .price(request.getPrice())
                .durationInMinutes((request.getDurationInMinutes()))
                .subject(subjectRepository.findById(request.getSubjectId()).orElseThrow(
                        () -> new SubjectNotFoundException("Subject not found.")))
                .done(false)
                .cancelled(false)
                .createdAt(timeNow)
                .updatedAt(timeNow)
                .user(userContextService.getLoggedOnUser())
                .build();
    }
}
