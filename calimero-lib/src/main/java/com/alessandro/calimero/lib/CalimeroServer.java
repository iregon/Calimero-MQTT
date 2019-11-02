package com.alessandro.calimero.lib;

import com.alessandro.calimero.utils.config.InstallationConfiguration;
import com.alessandro.knx.client.KnxConnectionHandler;
import com.alessandro.mqtt.client.ConnectionProfile;
import com.alessandro.mqtt.client.MqttConnectionHandler;

public class CalimeroServer {

    private MqttConnectionHandler mqttConnection;
    private KnxConnectionHandler knxConnection;

    private MqttToKnx mqttToKnx;
    private KnxToMqtt knxToMqtt;

    private InstallationConfiguration configuration;

    public CalimeroServer(InstallationConfiguration configuration) {
        this.configuration = configuration;

        mqttConnection = new MqttConnectionHandler(configuration);

        knxConnection = new KnxConnectionHandler(configuration);

        ConnectionProfile profile = new ConnectionProfile(
                configuration.getMqttConnectionOptions().getMqttBrokerAddress(),
                configuration.getMqttConnectionOptions().getMqttBrokerPort(),
                configuration.getMqttConnectionOptions().getMqttUsername(),
                configuration.getMqttConnectionOptions().getMqttPassword());
        mqttConnection.connect(profile);

        if(mqttConnection.isConnected()) {
            mqttToKnx = new MqttToKnx(mqttConnection, knxConnection);
            mqttToKnx.subscribeToMqttTopics(configuration);
            mqttConnection.addMessageObserver(mqttToKnx);

            knxToMqtt = new KnxToMqtt(mqttConnection);
            knxConnection.addTelegramObserver(knxToMqtt);
        }
    }
}
