package com.example.TeacherAppServer.service;

import com.example.TeacherAppServer.domain.dto.request.CreateSubjectRequest;
import com.example.TeacherAppServer.domain.model.StudentSubject;
import com.example.TeacherAppServer.domain.model.User;
import com.example.TeacherAppServer.mapper.SubjectMapper;
import com.example.TeacherAppServer.repository.SubjectRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class SubjectDaoService implements SubjectService {
    private final AuthenticationService authenticationService;
    private final SubjectRepository repository;
    private final SubjectMapper mapper;

    @Override
    public void save(CreateSubjectRequest request) {
        final User loggedUser = authenticationService.getLoggedUser();
        final StudentSubject subject = mapper.mapRequestToSubject(request, loggedUser);
        repository.save(subject);
    }

    @Override
    public StudentSubject getSubjectById(Integer id) {
        return null;
    }
}
