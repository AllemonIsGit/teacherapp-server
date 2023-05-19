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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Service;

import java.beans.FeatureDescriptor;
import java.time.LocalDateTime;
import java.util.stream.Stream;

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

        Integer amount = request.getAmount();

        for (int i = 0; i < amount; i++) {

            request.setDate(request.getDate().plusWeeks(i));
            lessonRepository.save(lessonMapper.toLesson(request));
        }
    }

    @Override
    public void put(Integer id, CreateLessonRequest request) {
        Lesson lesson = lessonRepository.findById(id).orElseThrow(() ->
                new LessonNotFoundException("Lesson not found."));

        BeanUtils.copyProperties(request, lesson, getNullPropertyNames(request));
        lesson.setUpdatedAt(LocalDateTime.now());

        lessonRepository.save(lesson);
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

    private String[] getNullPropertyNames(Object object) {
        final BeanWrapper wrapper = new BeanWrapperImpl(object);

        return Stream.of(wrapper.getPropertyDescriptors())
                .map(FeatureDescriptor::getName)
                .filter(propertyName -> wrapper.getPropertyValue(propertyName) == null)
                .toArray(String[]::new);
    }
}
