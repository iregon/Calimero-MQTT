package com.alessandro.mqtt.client;

import com.alessandro.calimero.utils.rxjava.ObservableList;
import com.alessandro.logger.Logger;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import com.alessandro.mqtt.client.utils.UUIDGenerator;

import java.text.MessageFormat;

/**
 * @author Alessandro Tornesello
 */

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
            Logger.getInstance().info("Connected to MQTT broker.");
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
                System.out.println(e.getMessage());
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
            Logger.getInstance().info(MessageFormat.format("ERROR ConnHandler: {0}", e.getMessage()));
        }
    }

    public ObservableList<MqttMessageExtended> getObservableMessageList() {
        return listener.getObservableMessageList();
    }

    public boolean isConnected() {
        return isConnected;
    }
}
