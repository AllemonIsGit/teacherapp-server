package com.example.TeacherAppServer.domain.exception;

public class NicknameNotProvidedException extends AuthenticationException {

    public NicknameNotProvidedException(String message) {
        super(message);
    }
}
