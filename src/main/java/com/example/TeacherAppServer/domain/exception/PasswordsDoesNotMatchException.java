package com.example.TeacherAppServer.domain.exception;

public class PasswordsDoesNotMatchException extends AuthenticationException {

    public PasswordsDoesNotMatchException(String message) {
        super(message);
    }
}
