package com.growtransparency.dtos;

import com.growtransparency.models.Status;

public class StatusDTO {
    private Long id;

    private String name;

    public StatusDTO(Status status) {
        this.id = status.getId();
        this.name = status.getName();
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
}
