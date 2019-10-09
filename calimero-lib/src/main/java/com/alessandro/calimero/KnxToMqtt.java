package com.alessandro.calimero;

import com.alessandro.mqtt.client.MqttConnectionHandler;

/**
 * @author Alessandro Tornesello
 */

public class KnxToMqtt {

    private MqttConnectionHandler connectionHandler;

    public KnxToMqtt(MqttConnectionHandler connectionHandler) {
        this.connectionHandler = connectionHandler;
    }

}
