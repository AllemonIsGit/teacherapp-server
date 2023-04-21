package com.example.TeacherAppServer.mapper;

import com.example.TeacherAppServer.domain.dto.request.CreateSubjectRequest;
import com.example.TeacherAppServer.domain.model.Subject;
import com.example.TeacherAppServer.domain.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
class SubjectMapperTest {

    @Test
    void toSubjectTest() {
        //given
        CreateSubjectRequest testRequest = new CreateSubjectRequest("Johnny", 35);

        User testUser = User.builder()
                .nickname("Nickname")
                .password("Password")
                .username("mYloginYo211")
                .dateCreated(LocalDateTime.now())
                .build();

        //when
        SubjectMapper underTest = new SubjectMapper(null);
        Subject testResultSubject = underTest.toSubject(testRequest, testUser);

        //then
        assertThat(testResultSubject.getName()).isEqualTo("Johnny");
        assertThat(testResultSubject.getPayPerSession()).isEqualTo(35);
    }

    // public Subject toSubject(CreateSubjectRequest request, User loggedUser) {
    //        return Subject.builder()
    //                .name(request.getName())
    //                .payPerSession(request.getPayPerSession())
    //                .user(loggedUser)
    //                .build();
    //    }
}