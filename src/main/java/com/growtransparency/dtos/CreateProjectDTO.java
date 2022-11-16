package com.growtransparency.dtos;

import com.growtransparency.models.Project;

import javax.validation.constraints.NegativeOrZero;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public record CreateProjectDTO(
        @NotBlank(message = "Nome é um campo obrigatório") String name,
        String description,
        @NotNull(message = "Custo é um campo obrigatório") Double cost,
        @NotNull(message = "Tempo total é um campo obrigatório") Integer totalTime,
        Integer status,
        @NotNull(message = "Pontuação é um campo obrigatório") Double score,
        String link) {
  public Project toEntity() {
    return new Project(name, description, cost, totalTime, status, score, link);
  }
}
