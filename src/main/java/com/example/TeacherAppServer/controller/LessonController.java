package com.example.TeacherAppServer.controller;

import com.example.TeacherAppServer.domain.dto.request.CreateLessonRequest;
import com.example.TeacherAppServer.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/lessons")
@RequiredArgsConstructor
public class LessonController {
    private final LessonService lessonService;

    @PostMapping
    public ResponseEntity<String> createLesson(@RequestBody @Valid CreateLessonRequest request) {
        lessonService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED).body("Lesson created.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLesson(@PathVariable Integer id) {
        lessonService.delete(id);

        return ResponseEntity.status(HttpStatus.OK).body("Lesson deleted.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> PatchLesson(@PathVariable Integer id, @RequestBody @Valid CreateLessonRequest request) {
        lessonService.put(id, request);

        return ResponseEntity.status(HttpStatus.OK).body("Lesson updated");
    }
}
