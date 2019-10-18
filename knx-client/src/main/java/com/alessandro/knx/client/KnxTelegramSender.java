package com.alessandro.knx.client;

import com.alessandro.logger.Logger;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import tuwien.auto.calimero.GroupAddress;
import tuwien.auto.calimero.KNXException;
import tuwien.auto.calimero.KNXTimeoutException;
import tuwien.auto.calimero.datapoint.CommandDP;
import tuwien.auto.calimero.datapoint.Datapoint;
import tuwien.auto.calimero.datapoint.StateDP;
import tuwien.auto.calimero.link.KNXLinkClosedException;
import tuwien.auto.calimero.process.ProcessCommunicator;

import java.text.MessageFormat;
import java.util.UUID;

public class KnxTelegramSender {

    private ProcessCommunicator communicator;

    public KnxTelegramSender(ProcessCommunicator communicator) {
        this.communicator = communicator;
    }

    /**
     * Method used to send a command to a KNX device
     * @param dp The datapoint
     * @param value The value to send to the device
     * @return TRUE if everything went fine
     */
    public boolean sendDataToDevice(Datapoint dp, String value){
        // TODO add logger
        try {
            this.communicator.write(dp, value);
            Logger.info(MessageFormat.format(
                    "Sent message to device with address {0} successfully.",
                    dp.getMainAddress().toString()));
            return true;
        } catch (KNXTimeoutException e) {
            Logger.info("Timeout ocurred while trying to send a write to the KNX network. Aborting send.");
            return false;
        }
        catch (KNXLinkClosedException e) {
            Logger.info("The link was closed while trying to send a command. Aborting send.");
            return false;
        } catch (KNXException e) {
            Logger.info("KNX generic exception.");
            return false;
        }
    }
}
