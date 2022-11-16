package com.growtransparency.controllers;

import com.growtransparency.dtos.CreateProjectDTO;
import com.growtransparency.dtos.ReturnDetailProjectDTO;
import com.growtransparency.dtos.ReturnProjectsDTO;
import com.growtransparency.dtos.UpdateProjectDTO;
import com.growtransparency.models.Project;
import com.growtransparency.repositories.ProjectRepository;
import com.growtransparency.services.ProjectService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/project")
@SecurityRequirement(name = "bearerAuth")
public class ProjectController {

  private final ProjectRepository projectRepository;
  private final ProjectService projectService;

  public ProjectController(ProjectRepository projectRepository,
                           ProjectService projectService) {
    this.projectRepository = projectRepository;
    this.projectService = projectService;
  }

  @PostMapping
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Project created"),
    @ApiResponse(responseCode = "403", description = "User authenticated but not authorized", content = @Content),
  })
  public ResponseEntity<ReturnDetailProjectDTO> createProject(@Valid @RequestBody CreateProjectDTO dto) {
    Project project = projectRepository.save(dto.toEntity());
    return ResponseEntity.ok(new ReturnDetailProjectDTO(project));
  }

  @GetMapping
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Projects returned"),
    @ApiResponse(responseCode = "204", description = "Doesn't have any Projects to return", content = @Content),
    @ApiResponse(responseCode = "403", description = "User not authenticated", content = @Content),
  })
  public ResponseEntity<List<ReturnProjectsDTO>> showProjects() {
    List<Project> projects = projectRepository.findAll();

    if (projects.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ReturnProjectsDTO.convert(new ArrayList<>()));
    }

    return ResponseEntity.status(HttpStatus.OK).body(ReturnProjectsDTO.convert(projects));
  }

  @DeleteMapping("/{id}")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Project deleted"),
      @ApiResponse(responseCode = "403", description = "User authenticated but not authorized", content = @Content),
    })
  public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
      projectRepository.deleteById(id);
      return ResponseEntity.ok().build();
  }

  @PutMapping("/{id}")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Project updated"),
      @ApiResponse(responseCode = "403", description = "User authenticated but not authorized", content = @Content),
    })
  public ResponseEntity<ReturnDetailProjectDTO> updateProject(@Valid @RequestBody UpdateProjectDTO dto, @PathVariable Long id) {
    try {
      var detailProject = this.projectService.updateProject(dto, id);
      return ResponseEntity.ok(detailProject);
    } catch (Exception e) {
      return ResponseEntity.notFound().build();
    }
  }
}
