// https://github.com/calimero-project/calimero-core/blob/master/src/tuwien/auto/calimero/process/ProcessListener.java

// ProcessEvent groups to group address:
// https://github.com/calimero-project/calimero-core/blob/master/src/tuwien/auto/calimero/GroupAddress.java

package com.alessandro.knx.client;

import tuwien.auto.calimero.DetachEvent;
import tuwien.auto.calimero.GroupAddress;
import tuwien.auto.calimero.process.ProcessEvent;
import tuwien.auto.calimero.process.ProcessListener;

import java.util.Observable;

public class KnxTelegramListener extends Observable implements ProcessListener {

    public KnxTelegramListener() {}

    @Override
    public void groupReadRequest(ProcessEvent processEvent) {
        notifyTelegram(processEvent);
    }

    @Override
    public void groupReadResponse(ProcessEvent processEvent) {

    }

    @Override
    public void groupWrite(ProcessEvent processEvent) {

    }

    @Override
    public void detached(DetachEvent detachEvent) {

    }

    private void notifyTelegram(ProcessEvent processEvent) {
        this.setChanged();
        this.notifyObservers(processEvent);
    }
}
