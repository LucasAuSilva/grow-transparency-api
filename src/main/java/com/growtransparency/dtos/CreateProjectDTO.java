package com.growtransparency.dtos;

import com.growtransparency.models.Project;

import javax.validation.constraints.NotBlank;

public class CreateProjectDTO {
  @NotBlank(message = "É necessário inserir o nome")
  private String name;
  private String description;
  private Double cost;
  private Integer totalTime;
  private Integer status;
  private Double score;
  private String link;

  public CreateProjectDTO() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Double getCost() {
    return cost;
  }

  public void setCost(Double cost) {
    this.cost = cost;
  }

  public Integer getTotalTime() {
    return totalTime;
  }

  public void setTotalTime(Integer totalTime) {
    this.totalTime = totalTime;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public Double getScore() {
    return score;
  }

  public void setScore(Double score) {
    this.score = score;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  public Project toEntity() {
    return new Project(getName(), getDescription(), getCost(), getTotalTime(), getStatus(), getScore(), getLink());
  }
}
