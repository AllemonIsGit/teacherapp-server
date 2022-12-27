package com.example.TeacherAppServer.service;

import com.example.TeacherAppServer.domain.dto.request.CreateSubjectRequest;
import com.example.TeacherAppServer.domain.exception.AccessForbiddenException;
import com.example.TeacherAppServer.domain.exception.SubjectNotFoundException;
import com.example.TeacherAppServer.domain.model.Subject;
import com.example.TeacherAppServer.domain.model.User;
import com.example.TeacherAppServer.mapper.SubjectMapper;
import com.example.TeacherAppServer.repository.SubjectRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class SubjectDaoService implements SubjectService {
    private final AuthenticationService authenticationService;
    private final SubjectRepository subjectRepository;
    private final SubjectMapper subjectMapper;

    @Override
    public void save(CreateSubjectRequest request) {
        final User loggedUser = authenticationService.getLoggedUser();
        final Subject subject = subjectMapper.mapRequestToSubject(request, loggedUser);
        subjectRepository.save(subject);
    }

    @Override
    public Subject getSubjectById(Integer id) {
        return null;
    }

    @Override
    public void patchSubject(Integer id, CreateSubjectRequest createSubjectRequest) {
        User user = authenticationService.getLoggedUser();
        Subject newSubject = subjectRepository.findById(id)
                .orElseThrow(() -> new SubjectNotFoundException("Subject not found."));
        if (!user.equals(newSubject.getUser())) {
            throw new AccessForbiddenException("Forbidden.");
        }
        if (createSubjectRequest.getName() != null) {
            newSubject.setName(createSubjectRequest.getName());
        }
        if (createSubjectRequest.getPayPerSession() != null) {
            newSubject.setPayPerSession(createSubjectRequest.getPayPerSession());
        }
        subjectRepository.save(newSubject);

    }


}
