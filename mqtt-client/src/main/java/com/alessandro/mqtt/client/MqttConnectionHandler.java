package com.alessandro.mqtt.client;

import com.alessandro.calimero.utils.config.InstallationConfiguration;
import com.alessandro.logger.Logger;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import com.alessandro.mqtt.client.utils.UUIDGenerator;

import java.text.MessageFormat;
import java.util.Observer;

public class MqttConnectionHandler {

    private MqttClient client;
    private MqttMessageListener listener = new MqttMessageListener();

    // True if client is connected to broker, otherwise false.
    private boolean isConnected = false;

    public MqttConnectionHandler(InstallationConfiguration configuration) {

    }

    public boolean connect(ConnectionProfile profile) {
        if(isConnected) return false;

        String brokerAddress = getBrokerAddress(profile.getAddress(), profile.getPort());
        Logger.info(MessageFormat.format("Connecting to MQTT broker {0} ...", brokerAddress));
        try {
            client = new MqttClient(brokerAddress, UUIDGenerator.generateRandom());
            MqttConnectOptions options = new MqttConnectOptions();
            options.setUserName(profile.getUsername());
            options.setPassword(profile.getPassword().toCharArray());
            client.connect(options);
            isConnected = true;
        } catch (MqttException e) {
            Logger.info(MessageFormat.format("Connection failed to MQTT broker: {0}", e.getMessage()));
            return false;
        }
        Logger.info("Connected to MQTT broker.");
        return true;
    }

    public boolean disconnect() {
        if (isConnected) {
            try {
                client.disconnect();
            } catch (MqttException e) {
                Logger.info(MessageFormat.format(
                        "ERROR MqttConnHandler(disconnect): {0}",
                        e.getMessage()));
                return false;
            }
        }
        return true;
    }

    public String getBrokerAddress(String address, String port) {
        return MessageFormat.format("tcp://{0}:{1}", address, port);
    }

    public void subscribe(String topic) {
        try {
            client.subscribe(topic, listener);
            Logger.info(MessageFormat.format("Subscribed to: {0}", topic));
        } catch (MqttException e) {
            Logger.info(MessageFormat.format("ERROR MqttConnHandler(subscribe): {0}", e.getMessage()));
        }
    }

    public void addMessageObserver(Observer ob) {
        listener.addObserver(ob);
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void publish(String topic, String payload, boolean isRetained) {
        publish(topic, payload.getBytes(), isRetained);
    }

    public void publish(String topic, byte[] payload, boolean isRetained) {
        try {
            client.publish(topic, payload, 1, isRetained);
        } catch (MqttException e) {
            Logger.info(MessageFormat.format(
                    "ERROR MqttConnHandler(publish): {0}",
                    e.getMessage()));
        }
    }
}
