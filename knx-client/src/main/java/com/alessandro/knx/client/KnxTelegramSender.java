package com.alessandro.knx.client;

import com.alessandro.logger.Logger;
import tuwien.auto.calimero.*;
import tuwien.auto.calimero.datapoint.Datapoint;
import tuwien.auto.calimero.link.KNXLinkClosedException;
import tuwien.auto.calimero.process.ProcessCommunicator;

import java.text.MessageFormat;

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
                    "Sent message \"{0}\" to device with address {1} successfully.",
                    value,
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
