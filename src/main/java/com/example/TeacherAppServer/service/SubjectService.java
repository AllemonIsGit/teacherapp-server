package com.example.TeacherAppServer.service;

import com.example.TeacherAppServer.domain.dto.request.CreateSubjectRequest;
import com.example.TeacherAppServer.domain.model.Subject;

public interface SubjectService {
    void save(CreateSubjectRequest request);
    Subject getSubjectById(Integer id);

    void patchSubject (Integer id, CreateSubjectRequest createSubjectRequest);
}
