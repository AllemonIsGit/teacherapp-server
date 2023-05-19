package com.example.TeacherAppServer.service;

import com.example.TeacherAppServer.domain.dto.request.CreateLessonRequest;
import com.example.TeacherAppServer.domain.dto.request.CreateSubjectRequest;

public interface LessonService {
    void save(CreateLessonRequest request);
    void put(Integer id, CreateLessonRequest request);
    void delete(Integer id);
}
