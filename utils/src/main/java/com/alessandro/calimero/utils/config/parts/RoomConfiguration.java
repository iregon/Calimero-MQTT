package com.alessandro.calimero.utils.config.parts;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class RoomConfiguration {

    private String name;

    private ArrayList<DeviceConfiguration> devices = new ArrayList<>();

    public RoomConfiguration(LinkedHashMap<String, Object> map) {
        this.name = (String)map.get("name");
        ((ArrayList)((LinkedHashMap<String, Object>)map.get("devices")).get("device")).forEach(
                device -> devices.add(new DeviceConfiguration((LinkedHashMap<String, Object>)device))
        );
    }

    public String getName() {
        return name;
    }

    public ArrayList<DeviceConfiguration> getDevices() {
        return devices;
    }
}
