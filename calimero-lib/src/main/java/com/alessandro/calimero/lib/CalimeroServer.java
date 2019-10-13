package com.alessandro.calimero.lib;

import com.alessandro.calimero.utils.config.ConfigurationHandler;
import com.alessandro.knx.client.KnxConnectionHandler;
import com.alessandro.mqtt.client.ConnectionProfile;
import com.alessandro.mqtt.client.MqttConnectionHandler;

public class CalimeroServer {

    private final String INSTALLATION_RESOURCE_PATH = "installation.json";

    private MqttConnectionHandler mqttConnection;
    private KnxConnectionHandler knxconnection;

    private MqttToKnx mqttToKnx;
    private KnxToMqtt knxToMqtt;

    private ConfigurationHandler configHandler;

    public CalimeroServer() {
        // Connect to broker
        mqttConnection = new MqttConnectionHandler();
        knxconnection = new KnxConnectionHandler();
        configHandler = new ConfigurationHandler(INSTALLATION_RESOURCE_PATH);

        ConnectionProfile profile = new ConnectionProfile(
                configHandler.getInstallationConfiguration().getMqttBrokerAddress(),
                configHandler.getInstallationConfiguration().getMqttBrokerPort());
        mqttConnection.connect(profile);

        if(mqttConnection.isConnected()) {
            mqttToKnx = new MqttToKnx(mqttConnection, configHandler.getInstallationConfiguration());
            mqttConnection.addMessageObserver(mqttToKnx);

            knxToMqtt = new KnxToMqtt(mqttConnection);
        }
    }
}
