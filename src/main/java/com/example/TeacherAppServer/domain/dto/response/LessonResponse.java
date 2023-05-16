package com.example.TeacherAppServer.domain.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class LessonResponse {
    private Integer id;
    private LocalDateTime date;
    private Integer price;
    private Integer durationInMinutes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean done;
    private boolean cancelled;
    private Integer subjectId;
}
