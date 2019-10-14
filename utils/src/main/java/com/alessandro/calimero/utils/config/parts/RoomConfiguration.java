package com.alessandro.calimero.utils.config.parts;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class RoomConfiguration {

    @JsonProperty("label")
    private String label;

    @JsonProperty("devices")
    private ArrayList<DeviceConfiguration> devices = new ArrayList<>();

    public RoomConfiguration() {
    }

    public String getLabel() {
        return label;
    }

    public ArrayList<DeviceConfiguration> getDevices() {
        return devices;
    }
}
