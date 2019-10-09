package com.alessandro.mqtt.client;

public class ConnectionProfile {

    private String address;
    private String port;

    public ConnectionProfile(String address, String port) {
        this.address = address;
        this.port = port;
    }

    public String getAddress() {
        return address;
    }

    public String getPort() {
        return port;
    }
}
