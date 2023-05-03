package com.example.TeacherAppServer.domain.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, unique = true)
    @Basic(optional = false)
    private Integer id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private LocalDateTime date;
    @Column(nullable = false)
    private Boolean isRecurring;
    @Column(nullable = false)
    private RecurringPattern RECURRING_PATTERN;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @Column(nullable = false)
    private LocalDateTime UpdatedAt;
    @Column(nullable = false)
    @ManyToOne
    private User createdBy;

}
