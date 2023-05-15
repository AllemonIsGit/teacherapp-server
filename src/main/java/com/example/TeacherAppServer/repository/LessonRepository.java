package com.example.TeacherAppServer.repository;

import com.example.TeacherAppServer.domain.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, Integer> {

}
