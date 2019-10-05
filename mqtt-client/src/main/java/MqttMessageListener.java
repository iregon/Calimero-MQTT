import javafx.collections.*;
import org.eclipse.paho.client.mqttv3.*;

import java.util.*;

/**
 * @author Alessandro Tornesello
 */

public class MqttMessageListener implements IMqttMessageListener {

    private ArrayList<MqttMessageExtended> messageList = new ArrayList<MqttMessageExtended>();
    private ObservableList<MqttMessageExtended> observableMessageList = FXCollections.observableList(messageList);

    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        MqttMessageExtended newMessage = new MqttMessageExtended(s, mqttMessage);
        messageList.add(newMessage);
    }

    public ObservableList<MqttMessageExtended> getObservableMessageList() {
        return observableMessageList;
    }
}
