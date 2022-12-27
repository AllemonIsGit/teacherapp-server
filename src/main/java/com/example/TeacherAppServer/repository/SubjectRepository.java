package com.example.TeacherAppServer.repository;

import com.example.TeacherAppServer.domain.model.Subject;
import com.example.TeacherAppServer.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    List<Subject> findAllByUser(User user);
}
