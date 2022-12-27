package com.example.TeacherAppServer.service;

import com.example.TeacherAppServer.domain.dto.request.CreateSubjectRequest;
import com.example.TeacherAppServer.domain.model.StudentSubject;

public interface SubjectService {
    void save(CreateSubjectRequest request);
    StudentSubject getSubjectById(Integer id);

    void patchSubject (Integer id, CreateSubjectRequest createSubjectRequest);
}
