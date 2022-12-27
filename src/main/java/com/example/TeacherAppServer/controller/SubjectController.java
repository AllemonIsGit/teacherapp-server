package com.example.TeacherAppServer.controller;


import com.example.TeacherAppServer.domain.dto.request.CreateSubjectRequest;
import com.example.TeacherAppServer.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/subjects")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    @PostMapping
    public ResponseEntity<String> createSubject(@RequestBody @Valid CreateSubjectRequest createSubjectRequest) {
        subjectService.save(createSubjectRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("Subject Created");
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> patchSubject(@PathVariable Integer id, @RequestBody @Valid CreateSubjectRequest createSubjectRequest) {
        subjectService.patchSubject(id, createSubjectRequest);
        return ResponseEntity.status(HttpStatus.OK).body("Subject Patched");
    }

}


