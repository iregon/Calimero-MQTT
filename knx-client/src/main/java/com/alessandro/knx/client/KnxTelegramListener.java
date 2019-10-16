package com.alessandro.knx.client;


import io.reactivex.Observable;
import tuwien.auto.calimero.process.ProcessCommunicator;

public class KnxTelegramListener extends Observable {

    public KnxTelegramListener(ProcessCommunicator communicator) {
        Observable.fromCallable(communicator.read());
    }

    boolean a = readBool;
}
