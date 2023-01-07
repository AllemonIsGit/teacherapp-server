package com.example.TeacherAppServer.service;

import com.example.TeacherAppServer.domain.dto.request.CreateSubjectRequest;
import com.example.TeacherAppServer.domain.dto.response.SubjectResponse;
import com.example.TeacherAppServer.domain.model.Subject;

import java.util.List;

public interface SubjectService {
    void save(CreateSubjectRequest request);

    void patchSubject (Integer id, CreateSubjectRequest createSubjectRequest);
    List<SubjectResponse> getSubjectsByUserAsResponse();
}
