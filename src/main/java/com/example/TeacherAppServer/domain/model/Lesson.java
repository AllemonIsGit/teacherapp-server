package com.example.TeacherAppServer.domain.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "lessons")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, unique = true)
    @Basic(optional = false)
    private Integer id;
    @Column(nullable = false)
    private LocalDateTime date;
    @Column(nullable = false)
    private Integer price;
    @Column(nullable = false)
    private Integer subjectId;
    @Column(nullable = false)
    private Integer durationInMinutes;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @Column(nullable = false)
    private LocalDateTime updatedAt;
    @Column(nullable = false)
    private boolean done;
    @Column(nullable = false)
    private boolean cancelled;
    @ManyToOne
    private User createdBy;

}
