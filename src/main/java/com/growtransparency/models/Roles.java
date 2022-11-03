package com.growtransparency.models;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity(name = "roles")
public class Roles implements GrantedAuthority {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public long id;

  @Column
  public String name;

  public Roles() {
  }

  public Roles(long id, String name) {
    this.id = id;
    this.name = name;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String getAuthority() {
    return this.name;
  }
}