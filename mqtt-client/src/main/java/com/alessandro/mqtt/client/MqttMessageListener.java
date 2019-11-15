package com.alessandro.mqtt.client;

import org.eclipse.paho.client.mqttv3.*;

import java.util.Observable;

public class MqttMessageListener extends Observable implements IMqttMessageListener {

    public MqttMessageListener() {super();}

    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        MqttMessageExtended newMessage = new MqttMessageExtended(s, mqttMessage);
        setChanged();
        notifyObservers(newMessage);
    }
}
