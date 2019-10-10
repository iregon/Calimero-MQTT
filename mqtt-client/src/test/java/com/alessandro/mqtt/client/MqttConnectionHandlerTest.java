package com.alessandro.mqtt.client;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MqttConnectionHandlerTest {

    ConnectionProfile localMosquitto = new ConnectionProfile("127.0.0.1", "5000");
    MqttConnectionHandler connectionHandler = new MqttConnectionHandler();

    @Test
    @Order(2)
    void connect() {
        Assertions.assertTrue(connectionHandler.connect(localMosquitto));

    }

    @Test
    @Order(3)
    void disconnect() {
//        com.alessandro.mqtt.client.MqttConnectionHandler.getInstance().connect(localMosquitto);
//        assertTrue(com.alessandro.mqtt.client.MqttConnectionHandler.getInstance().disconnect());
    }

    @Test
    @Order(1)
    void getBrokerAddress() {
        Assertions.assertEquals(
                connectionHandler.getBrokerAddress(
                        localMosquitto.getAddress(),
                        localMosquitto.getPort()),
                "tcp://127.0.0.1:5000");
    }


}