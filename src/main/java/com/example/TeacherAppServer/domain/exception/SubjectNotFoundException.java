package com.example.TeacherAppServer.domain.exception;

public class SubjectNotFoundException extends RuntimeException {

    public SubjectNotFoundException(String message) {
        super(message);
    }
}
