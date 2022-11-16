package com.growtransparency.settings.errors;

public class StatusNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private final String name;

    public StatusNotFoundException() {
        super("Status not found");
        this.name = "StatusNotFoundException";
    }

    public String getName() {
        return name;
    }
}
