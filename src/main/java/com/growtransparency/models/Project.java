package com.growtransparency.models;

import com.growtransparency.dtos.UpdateProjectDTO;

import javax.persistence.*;

@Entity(name = "projects")
public class Project {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column
  private String description;

  @Column(nullable = false)
  private Double cost;

  @Column(nullable = false)
  private Integer totalTime;

  @ManyToOne(optional = false)
  @JoinColumn(name = "status_id", nullable = false)
  private Status status;

  @Column(nullable = false)
  private Double score;

  @Column
  private String link;

  public Project() {
  }

  public Project(String name, String description, Double cost, Integer totalTime, Status status, Double score, String link) {
    this.name = name;
    this.description = description;
    this.cost = cost;
    this.totalTime = totalTime;
    this.status = status;
    this.score = score;
    this.link = link;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public void updateFields(UpdateProjectDTO dto, Status status) {
    this.name = dto.name();
    this.description = dto.description();
    this.cost = dto.cost();
    this.totalTime = dto.totalTime();
    this.status = status;
    this.score = dto.score();
    this.link = dto.link();
  }
}
