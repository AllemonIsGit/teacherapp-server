package com.example.TeacherAppServer.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.security.auth.Subject;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateSessionRequest {
    private int subjectId;
}
