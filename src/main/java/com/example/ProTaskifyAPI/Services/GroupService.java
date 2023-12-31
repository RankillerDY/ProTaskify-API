package com.example.ProTaskifyAPI.Services;

import com.example.ProTaskifyAPI.DTO.GroupDTO;
import com.example.ProTaskifyAPI.DTO.ResponseObject;
import com.example.ProTaskifyAPI.DTO.Resquest.CreateGroupRequest;
import org.springframework.http.ResponseEntity;

public interface GroupService {

    ResponseEntity<ResponseObject> createGroup(CreateGroupRequest g);

    ResponseEntity<ResponseObject> findGroupDetails(Integer group_id, Integer class_id);

    ResponseEntity<ResponseObject> findStudentsTasks(int groupId, int classId);

    ResponseEntity<ResponseObject> findStudentTask(int groupId, int classId);

    ResponseEntity<ResponseObject> chooseTopic(Integer topicId, Integer groupId);

    ResponseEntity<ResponseObject> getTotalTasksGroupStatus(Integer projectID, Integer groupId);
//    ResponseEntity<ResponseObject> chooseTopic(String topic);
}
