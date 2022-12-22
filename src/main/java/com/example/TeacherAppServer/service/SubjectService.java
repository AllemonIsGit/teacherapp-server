package com.example.TeacherAppServer.service;

import com.example.TeacherAppServer.domain.dto.request.CreateSubjectRequest;
import com.example.TeacherAppServer.domain.model.StudentSubject;
import com.example.TeacherAppServer.domain.model.User;

import java.util.List;

public interface SubjectService {
    void save(CreateSubjectRequest request);
    StudentSubject getSubjectById(Integer id);
}
