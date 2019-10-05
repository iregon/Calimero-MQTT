import javafx.collections.ObservableList;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import utils.UUIDGenerator;

import java.text.MessageFormat;

/**
 * @author Alessandro Tornesello
 */

public class MqttConnectionHandler {

    private static MqttConnectionHandler instance = new MqttConnectionHandler();

    private MqttClient client;
    private MqttMessageListener listener = new MqttMessageListener();

    // True if client is connected to broker, otherwise false.
    private boolean isConnected = false;

    public static MqttConnectionHandler getInstance() {
        return instance;
    }

    private MqttConnectionHandler() {
    }

    public boolean connect(ConnectionProfile profile) {
        String brokerAddress = getBrokerAddress(profile.getAddress(), profile.getPort());
        try {
            client = new MqttClient(brokerAddress, UUIDGenerator.generateRandom());
            isConnected = true;
            // TODO add logger
            return true;
        } catch (MqttException e) {
            // TODO add logger
            return false;
        }
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
            // TODO add logger
            e.printStackTrace();
        }
    }

    public ObservableList<MqttMessageExtended> getObservableMessageList() {
        return listener.getObservableMessageList();
    }
}
