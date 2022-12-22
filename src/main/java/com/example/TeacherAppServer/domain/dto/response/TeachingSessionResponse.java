package com.example.TeacherAppServer.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeachingSessionResponse {
    private Integer sessionId;
    private Integer price;
    private Integer subjectId;
}
