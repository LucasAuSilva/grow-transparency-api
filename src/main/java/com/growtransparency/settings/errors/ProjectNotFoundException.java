package com.growtransparency.settings.errors;

public class ProjectNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private final String name;

    public ProjectNotFoundException() {
        super("Project with this id was not found");
        this.name = "ProjectNotFoundException";
    }

    public String getName() {
        return name;
    }
}
