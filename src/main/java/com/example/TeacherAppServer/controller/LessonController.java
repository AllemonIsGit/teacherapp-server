package com.example.TeacherAppServer.controller;

import com.example.TeacherAppServer.domain.dto.request.CreateLessonRequest;
import com.example.TeacherAppServer.service.LessonDaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/lessons")
@RequiredArgsConstructor
public class LessonController {
    private final LessonDaoService lessonService;

    @PostMapping
    public ResponseEntity<String> createLesson(@RequestBody @Valid CreateLessonRequest request) {
        lessonService.save(request);

        return ResponseEntity.status(HttpStatus.OK).body("Lesson created.");
    }
}
