package com.growtransparency.dtos;

import com.growtransparency.models.Project;

import java.util.List;

public class ReturnProjectsDTO {

  private Long id;
  private String name;
  private String description;
  private Double cost;
  private Integer totalTime;
  private StatusDTO status;
  private Double score;
  private String link;

  public ReturnProjectsDTO(Project project) {
    this.id = project.getId();
    this.name = project.getName();
    this.description = project.getDescription();
    this.cost = project.getCost();
    this.totalTime = project.getTotalTime();
    this.status = new StatusDTO(project.getStatus());
    this.score = project.getScore();
    this.link = project.getLink();
  }

  public Long getId() { return id; }

  public void setId(Long id) { this.id = id; }

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

  public StatusDTO getStatus() {
    return status;
  }

  public void setStatus(StatusDTO status) {
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

  public static List<ReturnProjectsDTO> convert(List<Project> projects) {
    return projects.stream().map(ReturnProjectsDTO::new).toList();
  }
}

