package com.example.ProTaskifyAPI.ServiceImpl;

import com.example.ProTaskifyAPI.DTO.ResponseObject;
import com.example.ProTaskifyAPI.DTO.Resquest.CreateFeatureRequest;
import com.example.ProTaskifyAPI.Models.Feature;
import com.example.ProTaskifyAPI.Models.Process;
import com.example.ProTaskifyAPI.Models.ProcessCompositeKey;
import com.example.ProTaskifyAPI.Models.Sprint;
import com.example.ProTaskifyAPI.Repositories.FeatureRepo;
import com.example.ProTaskifyAPI.Repositories.ProcessRepo;
import com.example.ProTaskifyAPI.Repositories.ProjectRepo;
import com.example.ProTaskifyAPI.Repositories.SprintRepo;
import com.example.ProTaskifyAPI.Services.FeatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeatureServiceImpl implements FeatureService {
  private final FeatureRepo featureRepo;
  private final SprintRepo sprintRepo;
  private final ProcessRepo processRepo;
  private final ProjectRepo projectRepo;
  private final SprintServiceImpl sprintService;

  @Override
  public ResponseEntity<ResponseObject> createFeature(
      Integer sprintId, Integer projectId, CreateFeatureRequest createRequest) {
    try {
      Feature feature =
          Feature.builder()
              .feature_name(createRequest.getFeature_name())
              .description(createRequest.getDescription())
              .build();
      var savedFeature = featureRepo.save(feature);
      var automatic = autoAdd_Feature_To_Process(savedFeature, projectId, sprintId);
      if (automatic != null) {
        return automatic;
      }
      return ResponseEntity.ok(new ResponseObject("Successful", "Created sprint", savedFeature));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(new ResponseObject("Failed", "Create failed", null));
    }
  }

  @Override
  public ResponseEntity<ResponseObject> deleteFeature(
      Integer featureId, Integer projectId, Integer sprintId) {
    try {
      var feature = featureRepo.findById(featureId).orElse(null);
      var sprint = sprintRepo.findById(sprintId).orElse(null);
      var process =
          processRepo
              .findProcessByProjectAndSprintAndFeature(
                  projectRepo.findById(projectId).orElse(null),
                  sprint,
                  featureRepo.findById(featureId).orElse(null))
              .orElse(null);
      if (process != null) {
        processRepo.delete(process);
        sprintService.autoGenerated_Process(sprint, projectId, null);
      }
      if (feature != null) {
        featureRepo.delete(feature);
        return ResponseEntity.status(HttpStatus.OK)
            .body(new ResponseObject("Successfully", "Feature deleted", null));
      } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ResponseObject("Failed", "Can't find feature", null));
      }

    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.CONFLICT)
          .body(new ResponseObject("Failed", "Conflicted", e.getMessage()));
    }
  }

  public ResponseEntity<ResponseObject> autoAdd_Feature_To_Process(
      Feature feature, Integer projectId, Integer sprintId) {
    var sprint = sprintRepo.findById(sprintId).orElse(null);
    var process =
        processRepo
            .findProcessByProjectAndSprintAndFeature(
                projectRepo.findById(projectId).orElse(null), sprint,null)
            .orElse(null);

    if (process != null) {
      processRepo.delete(process);
      sprintService.autoGenerated_Process(sprint, projectId, feature.getFeature_id());
      return null;
    } else {
      return ResponseEntity.status(HttpStatus.NO_CONTENT)
          .body(new ResponseObject("Failed", "User must add sprint first", null));
    }
  }
}
