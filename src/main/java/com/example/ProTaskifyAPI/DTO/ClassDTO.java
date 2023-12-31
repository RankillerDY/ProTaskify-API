package com.example.ProTaskifyAPI.DTO;

import com.example.ProTaskifyAPI.Models.Group;
import com.example.ProTaskifyAPI.Models.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClassDTO {
    private int id;

    private String name;

    private int semesterID;

    private int lecturerID;

    private ArrayList<Student> classStudents;

    private ArrayList<Group> groupStudents;

}
