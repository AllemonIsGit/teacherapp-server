package com.example.TeacherAppServer.service;

import com.example.TeacherAppServer.domain.dto.request.CreateSubjectRequest;
import com.example.TeacherAppServer.domain.dto.response.SubjectResponse;
import com.example.TeacherAppServer.domain.exception.AccessForbiddenException;
import com.example.TeacherAppServer.domain.exception.SubjectNotFoundException;
import com.example.TeacherAppServer.domain.model.Subject;
import com.example.TeacherAppServer.mapper.SubjectMapper;
import com.example.TeacherAppServer.repository.SubjectRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class SubjectDaoService implements SubjectService {
    private final SubjectRepository subjectRepository;
    private final SubjectMapper subjectMapper;
    private final UserContextService userContextService;

    @Override
    public void save(CreateSubjectRequest request) {
        final Subject subject = subjectMapper.toSubject(request, userContextService.getLoggedOnUser());
        subjectRepository.save(subject);
    }

    public Subject getById(Integer id) {
        return subjectRepository.findById(id).orElseThrow(() ->
                new SubjectNotFoundException("Subject not found"));
    }

    @Override
    public void patchSubject(Integer id, CreateSubjectRequest createSubjectRequest) {
        Subject newSubject = subjectRepository.findById(id)
                .orElseThrow(() -> new SubjectNotFoundException("Subject not found."));
        if (!userContextService.isOwnerOfSubject(newSubject)) {
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

    @Override
    public List<SubjectResponse> getSubjectsByUserAsResponse() {
        List<Subject> subjects = subjectRepository.findAllByUser(userContextService.getLoggedOnUser());
        return subjectMapper.toSubjectResponseList(subjects);
    }

    @Override
    public void deleteById(int id) {
        if (!subjectRepository.existsByIdAndUser(id, userContextService.getLoggedOnUser())) {
            throw new AccessForbiddenException("Forbidden");
        }
        subjectRepository.deleteById(id);
    }


}
