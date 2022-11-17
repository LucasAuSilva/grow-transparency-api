package com.growtransparency.dtos;

import com.growtransparency.models.User;

import java.util.List;

public class ReturnAllUsersDTO {
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private List<String> roles;

    public ReturnAllUsersDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.roles = user.getRoles();
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public static List<ReturnAllUsersDTO> convert(List<User> users) {
        return users.stream().map(ReturnAllUsersDTO::new).toList();
    }
}
