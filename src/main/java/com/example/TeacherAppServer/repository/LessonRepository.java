package com.example.TeacherAppServer.repository;

import com.example.TeacherAppServer.domain.model.Lesson;
import com.example.TeacherAppServer.domain.model.Subject;
import com.example.TeacherAppServer.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Integer> {
    List<Subject> findAllByUser(User user);
}
