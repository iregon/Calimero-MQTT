package com.alessandro.calimero.utils.config;

import com.alessandro.calimero.utils.config.parts.BuildingConfiguration;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonRootName("installation")
public class InstallationConfiguration {

    @JsonProperty("name")
    private String installationName;

    @JsonProperty("mqttBrokerAddress")
    private String mqttBrokerAddress;

    @JsonProperty("mqttBrokerPort")
    private String mqttBrokerPort;

    @JsonProperty("localAddress")
    private String localAddress;

    @JsonProperty("buildings")
    private ArrayList<BuildingConfiguration> buildingsList = new ArrayList<>();

    @SuppressWarnings("unchecked")
//    private void addBuildings(Map<String,Object> buildings) {
//        ((ArrayList)buildings.get("building")).forEach(
//                building -> buildingsList.add(new BuildingConfiguration((LinkedHashMap<String, Object>)building)));
//    }

    public String getInstallationName() {
        return installationName;
    }

    public String getMqttBrokerAddress() {
        return mqttBrokerAddress;
    }

    public String getMqttBrokerPort() {
        return mqttBrokerPort;
    }

    public String getLocalAddress() {
        return localAddress;
    }

    public ArrayList<BuildingConfiguration> getBuildingsList() {
        return buildingsList;
    }
}


