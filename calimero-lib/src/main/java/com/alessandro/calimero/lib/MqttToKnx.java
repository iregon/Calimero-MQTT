package com.alessandro.calimero.lib;

import com.alessandro.mqtt.client.MqttConnectionHandler;
import com.alessandro.mqtt.client.MqttMessageExtended;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alessandro Tornesello
 */

public class MqttToKnx {

    private MqttConnectionHandler connectionHandler;
    private List<MqttMessageExtended> messages = new ArrayList<MqttMessageExtended>();

    public MqttToKnx(MqttConnectionHandler connectionHandler) {
        this.connectionHandler = connectionHandler;
        startMessageObserver();
    }

    private void startMessageObserver() {
        ObservableList<MqttMessageExtended> messageList = connectionHandler.getObservableMessageList();
        messageList.addListener((ListChangeListener<MqttMessageExtended>) change -> {
            System.out.println("Detected a change! ");
        });
    }
}
