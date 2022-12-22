package com.example.TeacherAppServer.domain.exception;

public class InvalidCreateUserRequestException extends RuntimeException {

    public InvalidCreateUserRequestException(String message) {
        super(message);
    }
}
