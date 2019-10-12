package com.alessandro.calimero.utils.config;

import com.fasterxml.jackson.annotation.JsonValue;

public enum DeviceType {
    SWITCH("SWITCH"),
    BOOL("BOOL");

    @JsonValue
    private final String displayName;

    DeviceType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
