package com.example.TeacherAppServer.service;

import com.example.TeacherAppServer.domain.dto.request.CreateSubjectRequest;
import com.example.TeacherAppServer.domain.exception.AccessForbiddenException;
import com.example.TeacherAppServer.domain.exception.SubjectNotFoundException;
import com.example.TeacherAppServer.domain.model.Subject;
import com.example.TeacherAppServer.mapper.SubjectMapper;
import com.example.TeacherAppServer.repository.SubjectRepository;
import com.example.TeacherAppServer.utill.UserHelper;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class SubjectDaoService implements SubjectService {
    private final SubjectRepository subjectRepository;
    private final SubjectMapper subjectMapper;
    private UserHelper userHelper;

    @Override
    public void save(CreateSubjectRequest request) {
        final Subject subject = subjectMapper.mapRequestToSubject(request, userHelper.getLoggedOnUser());
        subjectRepository.save(subject);
    }

    @Override
    public Subject getSubjectById(Integer id) {
        return null;
    }

    @Override
    public void patchSubject(Integer id, CreateSubjectRequest createSubjectRequest) {
        Subject newSubject = subjectRepository.findById(id)
                .orElseThrow(() -> new SubjectNotFoundException("Subject not found."));
        if (!userHelper.isOwnerOfSubject(newSubject)) {
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
