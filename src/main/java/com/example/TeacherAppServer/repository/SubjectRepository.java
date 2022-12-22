package com.example.TeacherAppServer.repository;

import com.example.TeacherAppServer.domain.model.StudentSubject;
import com.example.TeacherAppServer.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepository extends JpaRepository<StudentSubject, Integer> {
    List<StudentSubject> findAllByUser(User user);
}
