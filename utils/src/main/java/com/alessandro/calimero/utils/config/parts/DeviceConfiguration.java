package com.alessandro.calimero.utils.config.parts;

import java.util.LinkedHashMap;

public class DeviceConfiguration {

    private String name;
    private String knxAddress;

    public DeviceConfiguration(LinkedHashMap<String, Object> map) {
        this.name = (String)map.get("name");
        this.knxAddress = (String)map.get("knxAddress");
    }

    public String getName() {
        return name;
    }

    public String getKnxAddress() {
        return knxAddress;
    }
}
