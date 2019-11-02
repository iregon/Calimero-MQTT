package com.alessandro.calimero.utils.config;

import com.alessandro.calimero.utils.config.parts.FloorConfiguration;
import com.alessandro.calimero.utils.config.parts.MqttConnectionOption;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.ArrayList;

@JsonRootName("project")
public class InstallationConfiguration {

    @JsonProperty("name")
    private String installationName;

    @JsonProperty("mqttConnectionOptions")
    private MqttConnectionOption mqttConnectionOptions;

    @JsonProperty("knxServerAddress")
    private String knxServerAddress;

    @JsonProperty("knxServerPort")
    private String knxServerPort;

    @JsonProperty("localPort")
    private String localPort;

    @JsonProperty("floors")
    private ArrayList<FloorConfiguration> floorsList = new ArrayList<>();

    public String getInstallationName() {
        return installationName;
    }

    public MqttConnectionOption getMqttConnectionOptions() {
        return mqttConnectionOptions;
    }

    public String getKnxServerAddress() {
        return knxServerAddress;
    }

    public String getKnxServerPort() {
        return knxServerPort;
    }

    public String getLocalPort() {
        return localPort;
    }

    public ArrayList<FloorConfiguration> getFloorsList() {
        return floorsList;
    }
}


