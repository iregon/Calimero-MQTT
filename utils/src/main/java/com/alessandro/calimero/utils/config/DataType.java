package com.alessandro.calimero.utils.config;

import com.fasterxml.jackson.annotation.JsonValue;

public enum DataType {

    // B1
    SWITCH("1.001"),
    BOOL("1.002"),
    // ...
    UP_DOWN("1.008");

    @JsonValue
    private final String displayName;

    DataType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
