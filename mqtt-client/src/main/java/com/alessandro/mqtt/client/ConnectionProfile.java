package com.alessandro.mqtt.client;

public class ConnectionProfile {

    private String address;
    private String port;
    private String username;
    private String password;

    public ConnectionProfile(String address, String port, String username, String password) {
        this.address = address;
        this.port = port;
        this.username = username;
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public String getPort() {
        return port;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
