package com.example.TeacherAppServer.domain.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserDataResponse {
    private String nickname;
    private List<SubjectResponse> subjects;
}
