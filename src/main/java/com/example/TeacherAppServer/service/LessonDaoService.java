package com.example.TeacherAppServer.service;

import com.example.TeacherAppServer.domain.dto.request.CreateLessonRequest;
import com.example.TeacherAppServer.domain.exception.SubjectNotFoundException;
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

    @Override
    public void save(CreateLessonRequest request) {
        subjectRepository.findById(request.getSubjectId()).orElseThrow(() ->
                new SubjectNotFoundException("Subject not found."));

        lessonRepository.save(lessonMapper.toLesson(request));
    }

    @Override
    public void patch(Integer id) {

    }
}
