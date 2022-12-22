package com.example.TeacherAppServer.repository;

import com.example.TeacherAppServer.domain.model.StudentSubject;
import com.example.TeacherAppServer.domain.model.TeachingSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessionRepository extends JpaRepository<TeachingSession, Integer> {
    List<TeachingSession> findAllBySubject(StudentSubject subject);
}
