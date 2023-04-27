package com.example.TeacherAppServer.controller;


import com.example.TeacherAppServer.domain.dto.request.CreateSubjectRequest;
import com.example.TeacherAppServer.domain.dto.response.SubjectResponse;
import com.example.TeacherAppServer.domain.model.Subject;
import com.example.TeacherAppServer.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/subjects")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    @PostMapping
    public ResponseEntity<Map<String, String>> createSubject(@RequestBody @Valid CreateSubjectRequest createSubjectRequest) {
        subjectService.save(createSubjectRequest);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Subject Created");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> patchSubject(@PathVariable Integer id, @RequestBody @Valid CreateSubjectRequest createSubjectRequest) {
        subjectService.patchSubject(id, createSubjectRequest);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Subject Patched");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public List<SubjectResponse> getSubjects() {
        return subjectService.getSubjectsByUserAsResponse();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteSubject(@PathVariable Integer id) {
        subjectService.deleteById(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Subject Deleted");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}


