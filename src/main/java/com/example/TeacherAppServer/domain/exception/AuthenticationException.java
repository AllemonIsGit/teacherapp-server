package com.example.TeacherAppServer.domain.exception;

public abstract class AuthenticationException extends RuntimeException {

    public AuthenticationException(String message) {
        super(message);
    }
}

