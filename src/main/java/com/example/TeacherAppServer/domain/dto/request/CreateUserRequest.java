package com.example.TeacherAppServer.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {
    @Size(min = 3, max = 20, message = "Username has to be 3 to 20 characters long.")
    private String username;
    @Size(min = 5, max = 20, message = "Password has to be 5 to 20 characters long.")
    private String password;
    private String rePassword;
    private String nickname;
}
