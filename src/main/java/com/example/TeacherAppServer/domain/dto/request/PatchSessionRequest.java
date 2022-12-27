package com.example.TeacherAppServer.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatchSessionRequest {
    private Integer sessionId;
    private Integer price;
}
