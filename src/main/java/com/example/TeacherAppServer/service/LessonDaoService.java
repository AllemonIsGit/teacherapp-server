package com.example.TeacherAppServer.service;

import com.example.TeacherAppServer.domain.dto.request.CreateLessonRequest;
import com.example.TeacherAppServer.domain.exception.AccessForbiddenException;
import com.example.TeacherAppServer.domain.exception.LessonNotFoundException;
import com.example.TeacherAppServer.domain.exception.SubjectNotFoundException;
import com.example.TeacherAppServer.domain.model.Lesson;
import com.example.TeacherAppServer.domain.model.Subject;
import com.example.TeacherAppServer.mapper.LessonMapper;
import com.example.TeacherAppServer.repository.LessonRepository;
import com.example.TeacherAppServer.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LessonDaoService implements LessonService {
    private final LessonRepository lessonRepository;
    private final SubjectRepository subjectRepository;
    private final LessonMapper lessonMapper;
    private final UserContextService userContextService;

    @Override
    public void save(CreateLessonRequest request) {

        Subject subject = subjectRepository.findById(request.getSubjectId()).orElseThrow(() ->
                new SubjectNotFoundException("Subject not found."));

        if (!userContextService.isOwnerOfSubject(subject)) {
            throw new AccessForbiddenException("Forbidden");
        }

        lessonRepository.save(lessonMapper.toLesson(request));
    }

    @Override
    public void patch(Integer id) {

    }

    @Override
    public void delete(Integer id) {
        Lesson lesson = lessonRepository.findById(id).orElseThrow(() ->
                new LessonNotFoundException("Lesson not found."));

        if (!userContextService.isOwnerOfLesson(lesson)) {
            throw new AccessForbiddenException("Forbidden");
        }

        lessonRepository.deleteById(id);
    }
}
