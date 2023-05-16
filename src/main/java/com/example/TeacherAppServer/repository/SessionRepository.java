package com.example.TeacherAppServer.repository;

import com.example.TeacherAppServer.domain.model.Session;
import com.example.TeacherAppServer.domain.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessionRepository extends JpaRepository<Session, Integer> {
    List<Session> findAllBySubject(Subject subject);
}
