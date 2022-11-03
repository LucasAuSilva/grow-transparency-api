package com.growtransparency.controllers;

import com.growtransparency.dtos.CreateProjectDTO;
import com.growtransparency.dtos.ReturnCreatedProjectDTO;
import com.growtransparency.dtos.ReturnProjectsDTO;
import com.growtransparency.models.Project;
import com.growtransparency.repositories.ProjectRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/project")
@CrossOrigin
public class ProjectController {

  private final ProjectRepository projectRepository;

  public ProjectController(ProjectRepository projectRepository) {
    this.projectRepository = projectRepository;
  }

  @PostMapping
  public ResponseEntity<ReturnCreatedProjectDTO> createProject(@Valid @RequestBody CreateProjectDTO dto) {
    Project project = projectRepository.save(dto.toEntity());
    return ResponseEntity.ok(new ReturnCreatedProjectDTO(project));
  }

  @GetMapping
  public ResponseEntity<List<ReturnProjectsDTO>> showProjects() {
    List<Project> projects = projectRepository.findAll();

    if (projects.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ReturnProjectsDTO.convert(new ArrayList<>()));
    }

    return ResponseEntity.status(HttpStatus.OK).body(ReturnProjectsDTO.convert(projects));
  }
}
