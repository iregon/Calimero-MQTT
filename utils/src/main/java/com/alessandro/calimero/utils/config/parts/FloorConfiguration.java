package com.alessandro.calimero.utils.config.parts;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class FloorConfiguration {

    @JsonProperty("label")
    private String label;

    @JsonProperty("rooms")
    private ArrayList<RoomConfiguration> rooms = new ArrayList<>();

    public FloorConfiguration() {
    }

    public String getLabel() {
        return label;
    }

    public ArrayList<RoomConfiguration> getRooms() {
        return rooms;
    }
}
