package com.alessandro.calimero.utils.config.parts;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MqttConnectionOption {

    @JsonProperty("mqttBrokerAddress")
    private String mqttBrokerAddress;

    @JsonProperty("mqttUsername")
    private String mqttUsername;

    @JsonProperty("mqttPassword")
    private String mqttPassword;

    @JsonProperty("mqttBrokerPort")
    private String mqttBrokerPort;

    @JsonProperty("mqttBrokerWebsocketPort")
    private String mqttBrokerWebsocketPort;

    public String getMqttBrokerAddress() {
        return mqttBrokerAddress;
    }

    public String getMqttUsername() {
        return mqttUsername;
    }

    public String getMqttPassword() {
        return mqttPassword;
    }

    public String getMqttBrokerPort() {
        return mqttBrokerPort;
    }

    public String getMqttBrokerWebsocketPort() {
        return mqttBrokerWebsocketPort;
    }
}
