package com.example.TeacherAppServer.domain.model;

import lombok.*;


import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "sessions")
public class TeachingSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, unique = true)
    @Basic(optional = false)
    private int sessionId;
    @Column(nullable = false, updatable = false)
    private LocalDateTime sessionDate;
    @Column(nullable = false)
    private int price;
    @ManyToOne
    private StudentSubject subject;
}
