package com.alessandro.calimero.lib;

import com.alessandro.mqtt.client.MqttConnectionHandler;
import tuwien.auto.calimero.process.ProcessEvent;

import java.util.Observable;
import java.util.Observer;

public class KnxToMqtt implements Observer {

    private MqttConnectionHandler mqttConnectionHandler;


    public KnxToMqtt(MqttConnectionHandler mqttConnectionHandler) {
        this.mqttConnectionHandler = mqttConnectionHandler;
    }

    @Override
    public void update(Observable o, Object arg) {
        ProcessEvent event = (ProcessEvent)arg;
        String topic = TelegramToTopicMatcher.getStateTopicFromStateDp(event.getDestination().toString());
        mqttConnectionHandler.publish(topic, event.getASDU(), true);
    }
}
