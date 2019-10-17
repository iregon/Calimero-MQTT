package com.alessandro.knx.client;

import tuwien.auto.calimero.GroupAddress;
import tuwien.auto.calimero.datapoint.Datapoint;
import tuwien.auto.calimero.xml.KNXMLException;
import tuwien.auto.calimero.xml.XmlWriter;

public class DatapointImpl extends Datapoint {

    public DatapointImpl(GroupAddress main, String name, boolean stateBased) {
        super(main, name, stateBased);
    }

    @Override
    public void doSave(XmlWriter var1) throws KNXMLException {

    }
}
