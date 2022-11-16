package com.growtransparency.services;

import com.growtransparency.dtos.ReturnDetailProjectDTO;
import com.growtransparency.dtos.UpdateProjectDTO;
import com.growtransparency.models.Project;
import com.growtransparency.repositories.ProjectRepository;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public ReturnDetailProjectDTO updateProject(UpdateProjectDTO dto, Long Id) {
        Project project = projectRepository.findById(Id).orElseThrow(() -> new RuntimeException("Project not found"));
        project.updateFields(dto);
        projectRepository.save(project);
        return new ReturnDetailProjectDTO(project);
    }
}
