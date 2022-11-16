package com.growtransparency.services;

import com.growtransparency.dtos.CreateProjectDTO;
import com.growtransparency.dtos.ReturnDetailProjectDTO;
import com.growtransparency.dtos.UpdateProjectDTO;
import com.growtransparency.models.Project;
import com.growtransparency.models.Status;
import com.growtransparency.repositories.ProjectRepository;
import com.growtransparency.repositories.StatusRepository;
import com.growtransparency.settings.errors.ProjectNotFoundException;
import com.growtransparency.settings.errors.StatusNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final StatusRepository statusRepository;

    public ProjectService(ProjectRepository projectRepository, StatusRepository statusRepository) {
        this.projectRepository = projectRepository;
        this.statusRepository = statusRepository;
    }


    public ReturnDetailProjectDTO createProject(CreateProjectDTO dto) {
        var status = getStatus(dto.statusId());
        Project project = projectRepository.save(dto.toEntity(status));
        return new ReturnDetailProjectDTO(project);
    }

    public ReturnDetailProjectDTO updateProject(UpdateProjectDTO dto, Long Id) {
        Project project = projectRepository.findById(Id).orElseThrow(ProjectNotFoundException::new);
        var status = getStatus(dto.statusId());
        project.updateFields(dto, status);
        projectRepository.save(project);
        return new ReturnDetailProjectDTO(project);
    }

    private Status getStatus(Long statusId) {
        var status = statusRepository.findById(statusId);
        if (status.isEmpty()) {
            throw new StatusNotFoundException();
        }
        return status.get();
    }

}
