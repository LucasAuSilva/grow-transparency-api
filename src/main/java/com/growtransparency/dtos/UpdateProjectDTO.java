package com.growtransparency.dtos;

import com.growtransparency.models.Project;
import com.growtransparency.models.Status;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record UpdateProjectDTO(
        @NotBlank(message = "Nome é um campo obrigatório") String name,
        String description,
        @NotNull(message = "Custo é um campo obrigatório") Double cost,
        @NotNull(message = "Tempo total é um campo obrigatório") Integer totalTime,
        @NotNull @Min(1) Long statusId,
        @NotNull(message = "Pontuação é um campo obrigatório") Double score,
        String link) {
    public Project toEntity(Status status) {
        return new Project(name, description, cost, totalTime, status, score, link);
    }
}
