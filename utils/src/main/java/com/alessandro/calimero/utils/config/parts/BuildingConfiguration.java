package com.alessandro.calimero.utils.config.parts;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class BuildingConfiguration {
    private String name;
    private String remoteIpAddress;
    private String remotePort;

    private ArrayList<RoomConfiguration> rooms = new ArrayList<>();

//    public BuildingConfiguration(String name, String remoteIpAddress, String remotePort) {
//        this.name = name;
//        this.remoteIpAddress = remoteIpAddress;
//        this.remotePort = remotePort;
//    }

    public BuildingConfiguration(LinkedHashMap<String, Object> map) {
        this.name = (String)map.get("name");
        this.remoteIpAddress = (String)map.get("remoteIpAddress");
        this.remotePort = (String)map.get("remotePort");
        ((ArrayList)((LinkedHashMap<String, Object>)map.get("rooms")).get("room")).stream().forEach(
                room -> rooms.add(new RoomConfiguration((LinkedHashMap<String, Object>)room))
        );
    }

    public String getName() {
        return name;
    }

    public String getRemoteIpAddress() {
        return remoteIpAddress;
    }

    public String getRemotePort() {
        return remotePort;
    }

    public ArrayList<RoomConfiguration> getRooms() {
        return rooms;
    }
}
