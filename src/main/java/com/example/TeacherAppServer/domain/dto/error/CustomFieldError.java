package com.example.TeacherAppServer.domain.dto.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomFieldError {
    private String field;
    private String errorMessage;
}
