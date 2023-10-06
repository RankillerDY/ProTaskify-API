package com.example.ProTaskifyAPI.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "class")
@Builder
public class Class {
    @Id
    private String class_id;

    @Column(name = "class_name", length = 50)
    private String group_name;

    private String semester_id;

    @ManyToOne
    @JoinColumn(name = "lecturer_id")
    private Lecturer lecturerID;

    @OneToMany(mappedBy = "classID")
    private Set<Student> classStudents = new HashSet<>();

    @OneToMany(mappedBy = "classID")
    private Set<Group> groupStudents = new HashSet<>();

}
