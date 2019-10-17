package com.alessandro.knx.client;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import tuwien.auto.calimero.GroupAddress;
import tuwien.auto.calimero.KNXException;
import tuwien.auto.calimero.KNXTimeoutException;
import tuwien.auto.calimero.datapoint.Datapoint;
import tuwien.auto.calimero.link.KNXLinkClosedException;
import tuwien.auto.calimero.process.ProcessCommunicator;

import java.util.UUID;

public class KnxTelegramSender {

    private ProcessCommunicator communicator;

    public KnxTelegramSender(ProcessCommunicator communicator) {
        this.communicator = communicator;
    }

    /**
     * Method used to send a command to a KNX device
     * @param deviceAddress The group address of the device
     * @param value The value to send to the device TRUE or FALSE
     * @return TRUE if everything went fine
     */
    public boolean sendDataToDevice(GroupAddress deviceAddress, String dpt, String value){
        // TODO add logger
        //try to send data to a device
        try {
            System.out.println("Trying to send message to device with address " +  deviceAddress + "...");

            Datapoint dp = new Datapoint(deviceAddress, UUID.randomUUID().toString(), true);
            this.communicator.write(dp, value);
            System.out.println("Sent message to device with address " +  deviceAddress + " successfully");
            return true;
        } catch (KNXTimeoutException e) {
            System.out.println("Timeout ocurred while trying to send a write to the KNX network. Aborting send.");
            return false;
        }
        catch (KNXLinkClosedException e) {
            System.out.println("The link was closed while trying to send a command. Aborting send");
            return false;
        } catch (KNXException e) {
            e.printStackTrace();
        }
    }
}
