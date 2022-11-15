package com.growtransparency.models;

import javax.persistence.*;

@Entity(name = "status")
public class Status {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column
  private String name;

  @Column
  private String description;

  @Column
  private boolean ativo;

  public Status() {
  }

  public Status(Long id, String name, String description, boolean ativo) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.ativo = ativo;
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

  public boolean getAtivo() {
    return ativo;
  }

  public void setAtivo(boolean ativo) {
    this.ativo = ativo;
  }
}
