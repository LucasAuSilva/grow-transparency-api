package com.growtransparency.dtos;

import com.growtransparency.models.Project;

import javax.validation.constraints.NotBlank;

public record UpdateProjectDTO(
    @NotBlank String name,
    String description,
    Double cost,
    Integer totalTime,
    Integer status,
    Double score,
    String link) {
    public Project toEntity() {
        return new Project(name, description, cost, totalTime, status, score, link);
    }
}
