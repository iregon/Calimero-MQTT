package com.alessandro.calimero.lib;

import com.alessandro.calimero.utils.config.ConfigurationHandler;
import com.alessandro.mqtt.client.ConnectionProfile;
import com.alessandro.mqtt.client.MqttConnectionHandler;

public class CalimeroServer {

    private final String INSTALLATION_RESOURCE_PATH = "installation.json";

    private MqttConnectionHandler connectionHandler;
    private MqttToKnx mqttToKnx;
    private KnxToMqtt knxToMqtt;

    private ConfigurationHandler configHandler;

    public CalimeroServer() {
        // Connect to broker
        connectionHandler = new MqttConnectionHandler();
        configHandler = new ConfigurationHandler(INSTALLATION_RESOURCE_PATH);

        ConnectionProfile profile = new ConnectionProfile(
                configHandler.getInstallationConfiguration().getMqttBrokerAddress(),
                configHandler.getInstallationConfiguration().getMqttBrokerPort());
        connectionHandler.connect(profile);

        if(connectionHandler.isConnected()) {
            mqttToKnx = new MqttToKnx(connectionHandler, configHandler.getInstallationConfiguration());
            knxToMqtt = new KnxToMqtt(connectionHandler);
        }
    }
}
