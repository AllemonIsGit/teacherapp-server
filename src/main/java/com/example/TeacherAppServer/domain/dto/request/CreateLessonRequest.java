package com.example.TeacherAppServer.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateLessonRequest {
    private LocalDateTime date;
    private Integer price;
    private Integer durationInMinutes;
    private Integer amount;
    private Integer SubjectId;
}
