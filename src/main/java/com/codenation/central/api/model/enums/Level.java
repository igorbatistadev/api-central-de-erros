package com.codenation.central.api.model.enums;

public enum Level {
    ERROR("ERROR"),
    WARNING("WARNING"),
    INFO("INFO");

    private String level;

    private Level(String level) {
        this.level = level;
    }

    public String getLevel() {
        return level;
    }
}
