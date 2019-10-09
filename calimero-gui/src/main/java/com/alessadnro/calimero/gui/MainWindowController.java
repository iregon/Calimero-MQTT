package com.alessadnro.calimero.gui; /**
 * @author Alessandro Tornesello
 */

import com.alessandro.calimero.lib.CalimeroServer;
import javafx.fxml.FXML;

public class MainWindowController {

    CalimeroServer server;

    /**
     * Initialize method is executed before the gui loading
     */
    @FXML
    public void initialize() {
        server = new CalimeroServer();
    }
}
