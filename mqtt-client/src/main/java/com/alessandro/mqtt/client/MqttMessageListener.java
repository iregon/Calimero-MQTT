package com.alessandro.mqtt.client;

import com.alessandro.calimero.utils.rxjava.ObservableList;
import org.eclipse.paho.client.mqttv3.*;

public class MqttMessageListener implements IMqttMessageListener {

    private ObservableList<MqttMessageExtended> messageList;

    public MqttMessageListener() {
        messageList = new ObservableList<>();
    }

    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        MqttMessageExtended newMessage = new MqttMessageExtended(s, mqttMessage);
        messageList.add(newMessage);
    }

    public ObservableList<MqttMessageExtended> getObservableMessageList() {
        return messageList;
    }
}
