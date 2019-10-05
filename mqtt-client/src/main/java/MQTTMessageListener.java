import javafx.collections.*;
import org.eclipse.paho.client.mqttv3.*;

import java.util.*;

/**
 * @author Alessandro Tornesello
 */

public class MQTTMessageListener implements IMqttMessageListener {

    private ArrayList<MQTTMessageExtended> messageList = new ArrayList<MQTTMessageExtended>();
    private ObservableList<MQTTMessageExtended> observableMessageList = FXCollections.observableList(messageList);

    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        MQTTMessageExtended newMessage = new MQTTMessageExtended(s, mqttMessage);
        messageList.add(newMessage);
    }

    public ObservableList<MQTTMessageExtended> getObservableMessageList() {
        return observableMessageList;
    }
}
