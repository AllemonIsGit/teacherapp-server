package com.example.TeacherAppServer.repository;

import com.example.TeacherAppServer.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findUserByUsername(String username);
    boolean existsByUsername(String username);
}
