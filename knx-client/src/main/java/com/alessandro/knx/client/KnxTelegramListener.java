// https://github.com/calimero-project/calimero-core/blob/master/src/tuwien/auto/calimero/process/ProcessListener.java

// ProcessEvent groups to group address:
// https://github.com/calimero-project/calimero-core/blob/master/src/tuwien/auto/calimero/GroupAddress.java

// Example:
// https://www.internetmosquito.com/2012/12/learning-how-to-control-existing-knx.html

package com.alessandro.knx.client;

import tuwien.auto.calimero.DetachEvent;
import tuwien.auto.calimero.process.ProcessEvent;
import tuwien.auto.calimero.process.ProcessListener;

import java.util.Observable;

/**
 * Class used to capture KNX events from the EIBD server
 *
 * */
public class KnxTelegramListener extends Observable implements ProcessListener {

    public KnxTelegramListener() {}

    @Override
    public void groupReadRequest(ProcessEvent processEvent) {

    }

    @Override
    public void groupReadResponse(ProcessEvent processEvent) {

    }

    @Override
    /**
     * Callback method whenever something is written in the KNX network
     * @param Contains the information about the event occured
     */
    public void groupWrite(ProcessEvent processEvent) {
//        String destAddr = processEvent.getDestination().toString();
//
//        //Message value (state)
//        byte[] asdu = processEvent.getASDU();
//        //Check if state was correct
//        int state = ((asdu != null) && (asdu.length > 0)) ? asdu[0] : -1;
//
//        System.out.println("Group address is " + destAddr + "and state is " + state); // TODO add logger

        notifyTelegram(processEvent);
    }

    @Override
    public void detached(DetachEvent detachEvent) {
        System.out.println("The KNXNetworkLink has been disconnected from the Process Monitor"); // TODO add logger
    }

    private void notifyTelegram(ProcessEvent processEvent) {
        this.setChanged();
        this.notifyObservers(processEvent);
    }
}
