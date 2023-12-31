package com.example.ProTaskifyAPI.DTO;

import com.example.ProTaskifyAPI.Models.Messages;
import com.example.ProTaskifyAPI.Models.Project;
import com.example.ProTaskifyAPI.Models.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GroupDTO {

    private int id;

    private String name;

    private float score;

    private int projectID;

    private int classID;

    private Set<Student> groupStudents;

    private Set<Messages> messagesSet;


}
