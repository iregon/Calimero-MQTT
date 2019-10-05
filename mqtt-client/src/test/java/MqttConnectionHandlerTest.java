import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MQTTConnectionHandlerTest {

    ConnectionProfile localMosquitto = new ConnectionProfile("127.0.0.1", "1883");

    @Test
    @Order(2)
    void connect() {
        assertTrue(MQTTConnectionHandler.getInstance().connect(localMosquitto));

    }

    @Test
    @Order(3)
    void disconnect() {
//        MQTTConnectionHandler.getInstance().connect(localMosquitto);
//        assertTrue(MQTTConnectionHandler.getInstance().disconnect());
    }

    @Test
    @Order(1)
    void getBrokerAddress() {
        assertEquals(
                MQTTConnectionHandler.getInstance().getBrokerAddress(
                        localMosquitto.getAddress(),
                        localMosquitto.getPort()),
                "tcp://127.0.0.1:1883");
    }


}