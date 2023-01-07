package com.example.TeacherAppServer.controller;

import com.example.TeacherAppServer.domain.dto.request.CreateSessionRequest;
import com.example.TeacherAppServer.domain.dto.request.PatchSessionRequest;
import com.example.TeacherAppServer.domain.dto.response.SessionResponse;
import com.example.TeacherAppServer.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/sessions")
@RequiredArgsConstructor
public class SessionController {
    private final SessionService sessionService;

    @PostMapping
    public ResponseEntity<String> createSession(@RequestBody @Valid CreateSessionRequest request) {
        sessionService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("Session created.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> patchSession(@PathVariable Integer id, @RequestBody @Valid PatchSessionRequest request) {
        sessionService.patchSession(id, request);
        return ResponseEntity.status(HttpStatus.OK).body("Session updated.");
    }

    @GetMapping
    public List<SessionResponse> getSession() {
        return sessionService.getAllByUserAsResponse();
    }
}
