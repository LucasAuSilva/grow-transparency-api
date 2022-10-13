package com.growtransparency.models;

import javax.persistence.*;

@Entity(name = "projects")
public class Project {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column
  private String name;

  @Column
  private String description;

  @Column
  private Double cost;

  @Column
  private Integer totalTime;

  @Column
  private Integer status;

  @Column
  private Double score;

  @Column
  private String link;

  public Project() {
  }

  public Project(String name, String description, Double cost, Integer totalTime, Integer status, Double score, String link) {
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
}