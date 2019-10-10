package com.alessadnro.calimero.gui; /**
 * @author Alessandro Tornesello
 */

import com.alessadnro.calimero.gui.logs.TextflowLogger;
import com.alessandro.calimero.lib.CalimeroServer;
import com.alessandro.logger.Logger;
import com.alessandro.logger.interfaces.ILoggerHandler;
import javafx.fxml.FXML;
import javafx.scene.text.TextFlow;

public class MainWindowController {

    @FXML
    private TextFlow tflow_logs;

    private CalimeroServer server;

    private ILoggerHandler logger;

    /**
     * Initialize method is executed before the gui loading
     */
    @FXML
    public void initialize() {
        logger = new TextflowLogger(tflow_logs);
        Logger.getInstance().addLogger(logger);

        server = new CalimeroServer();
    }
}
