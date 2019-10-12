package com.alessandro.calimero.utils.config.parts;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class RoomConfiguration {

    @JsonProperty("name")
    private String name;

    @JsonProperty("devices")
    private ArrayList<DeviceConfiguration> devices = new ArrayList<>();

//    public RoomConfiguration(LinkedHashMap<String, Object> map) {
//        this.name = (String)map.get("name");
//        ((ArrayList)((LinkedHashMap<String, Object>)map.get("devices")).get("device")).forEach(
//                device -> devices.add(new DeviceConfiguration((LinkedHashMap<String, Object>)device))
//        );
//    }

    public RoomConfiguration() {
    }

    public String getName() {
        return name;
    }

    public ArrayList<DeviceConfiguration> getDevices() {
        return devices;
    }
}
