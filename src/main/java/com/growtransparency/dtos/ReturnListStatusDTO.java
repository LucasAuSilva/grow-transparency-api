package com.growtransparency.dtos;

import com.growtransparency.models.Status;

import java.util.List;

public class ReturnListStatusDTO {
    private Long id;
    private String name;

    public ReturnListStatusDTO(Status status) {
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

    public static List<ReturnListStatusDTO> convert(List<Status> status) {
        return status.stream().map(ReturnListStatusDTO::new).toList();
    }
}
