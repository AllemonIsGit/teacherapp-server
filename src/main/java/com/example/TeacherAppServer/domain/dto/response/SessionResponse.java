package com.example.TeacherAppServer.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SessionResponse {
    private Integer sessionId;
    private Integer price;
    private Integer subjectId;
    private LocalDateTime date;
}
