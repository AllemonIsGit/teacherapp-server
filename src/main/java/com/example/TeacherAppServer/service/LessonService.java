package com.example.TeacherAppServer.service;

import com.example.TeacherAppServer.domain.dto.request.CreateLessonRequest;

public interface LessonService {
    void save(CreateLessonRequest request);
    void patch(Integer id);
    void delete(Integer id);
}
