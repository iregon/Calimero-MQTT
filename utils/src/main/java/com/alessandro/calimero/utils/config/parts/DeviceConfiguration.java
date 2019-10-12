package com.alessandro.calimero.utils.config.parts;

import com.alessandro.calimero.utils.config.DeviceType;

import java.util.LinkedHashMap;

public class DeviceConfiguration {

    private String name;
    private String knxAddress;
    private DeviceType type;

    public DeviceConfiguration() {
    }

//    public DeviceConfiguration(LinkedHashMap<String, Object> map) {
////        this.name = (String)map.get("name");
////        this.knxAddress = (String)map.get("knxAddress");
////        this.type = DeviceType.valueOf(map.get("type"));
//    }

    public String getName() {
        return name;
    }

    public String getKnxAddress() {
        return knxAddress;
    }

    public DeviceType getType() {
        return type;
    }
}
