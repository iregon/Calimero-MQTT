package com.alessandro.mqtt.client;

import com.alessandro.logger.Logger;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import com.alessandro.mqtt.client.utils.UUIDGenerator;

import java.text.MessageFormat;
import java.util.Observer;

public class MqttConnectionHandler {

    private MqttClient client;
    private MqttMessageListener listener = new MqttMessageListener();

    // True if client is connected to broker, otherwise false.
    private boolean isConnected = false;

    public MqttConnectionHandler() {

    }

    public boolean connect(ConnectionProfile profile) {
        if(isConnected) return false;

        String brokerAddress = getBrokerAddress(profile.getAddress(), profile.getPort());
        try {
            client = new MqttClient(brokerAddress, UUIDGenerator.generateRandom());
            client.connect();
            isConnected = true;
        } catch (MqttException e) {
            Logger.getInstance().info("Connection failed to MQTT broker.");
            return false;
        }

        return true;
    }

    public boolean disconnect() {
        if (isConnected) {
            try {
                client.disconnect();
            } catch (MqttException e) {
                Logger.getInstance().info(MessageFormat.format(
                        "ERROR ConnHandler(disconnect): {0}",
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
        } catch (MqttException e) {
            Logger.getInstance().info(MessageFormat.format(
                    "ERROR ConnHandler(subscribe): {0}",
                    e.getMessage()));
        }
    }

    public void addMessageObserver(Observer ob) {
        listener.addObserver(ob);
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void publish(String topic, String payload) {
        try {
            client.publish(topic, payload.getBytes(), 1, true);
        } catch (MqttException e) {
            Logger.getInstance().info(MessageFormat.format(
                    "ERROR ConnHandler(publish): {0}",
                    e.getMessage()));
        }
    }
}
