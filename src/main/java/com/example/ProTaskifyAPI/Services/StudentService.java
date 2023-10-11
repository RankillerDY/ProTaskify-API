package com.example.ProTaskifyAPI.Services;

import com.example.ProTaskifyAPI.DTO.ResponseObject;
import com.example.ProTaskifyAPI.DTO.UpdateLinkRequest;
import org.springframework.http.ResponseEntity;

public interface StudentService {
    ResponseEntity<ResponseObject> updateLink(UpdateLinkRequest dto);

    boolean checkIfStudentInClass(int studentID);

    void setLeader(int studentID);

}