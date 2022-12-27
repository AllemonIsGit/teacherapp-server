package com.example.TeacherAppServer.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Data
@Entity
@Table(name = "subjects")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Subject {
    @Id
    @Column(nullable = false, unique = true, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Integer payPerSession;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "subject")
    private List<Session> sessions;
}
