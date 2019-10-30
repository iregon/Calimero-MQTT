// https://github.com/calimero-project/calimero-core/blob/master/src/tuwien/auto/calimero/process/ProcessListener.java

// ProcessEvent groups to group address:
// https://github.com/calimero-project/calimero-core/blob/master/src/tuwien/auto/calimero/GroupAddress.java

// Example:
// https://www.internetmosquito.com/2012/12/learning-how-to-control-existing-knx.html

package com.alessandro.knx.client;

import com.alessandro.logger.Logger;
import tuwien.auto.calimero.DetachEvent;
import tuwien.auto.calimero.process.ProcessEvent;
import tuwien.auto.calimero.process.ProcessListener;

import java.text.MessageFormat;
import java.util.Observable;

/**
 * Class used to capture KNX events from the EIBD server
 * */
public class KnxTelegramListener extends Observable implements ProcessListener {

    public KnxTelegramListener() {}

    @Override
    public void groupReadRequest(ProcessEvent processEvent) {

    }

    @Override
    public void groupReadResponse(ProcessEvent processEvent) {

    }

    /**
     * Callback method whenever something is written in the KNX network
     * @param processEvent Contains the information about the event occurred
     */
    @Override
    public void groupWrite(ProcessEvent processEvent) {
        Logger.info(MessageFormat.format("Group address is {0} and state is {1}",
                processEvent.getDestination().toString(),
                (char)(processEvent.getASDU()[0] + 48))); // Convert byte to 0 (48) and 1 (49) in ascii
        notifyTelegram(processEvent);
    }

    @Override
    public void detached(DetachEvent detachEvent) {
        Logger.info("The KNXNetworkLink has been disconnected from the Process Monitor");
    }

    private void notifyTelegram(ProcessEvent processEvent) {
        this.setChanged();
        this.notifyObservers(processEvent);
    }
}
