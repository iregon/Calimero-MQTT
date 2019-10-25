package com.alessandro.calimero.gui;

import com.alessandro.calimero.gui.logs.TextflowLogger;
import com.alessandro.calimero.lib.CalimeroServer;
import com.alessandro.calimero.utils.config.ConfigurationHandler;
import com.alessandro.logger.Logger;
import com.alessandro.logger.interfaces.ILoggerHandler;
import javafx.fxml.FXML;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class MainWindowController {

    private final String INSTALLATION_RESOURCE_PATH = "installation.json";

    @FXML
    private TextFlow tflow_logs;

    private Stage primaryStage;

    private CalimeroServer server;

    /**
     * Initialize method is executed before the gui loading
     */
    @FXML
    public void initialize() {
        createTextflowLogger();

        ConfigurationHandler config = new ConfigurationHandler(INSTALLATION_RESOURCE_PATH);
        updateWindowTitle("DomusCONTROL Server - " + config.getInstallationConfiguration().getInstallationName());

        server = new CalimeroServer(config.getInstallationConfiguration());
    }

    private void createTextflowLogger() {
        ILoggerHandler logger = new TextflowLogger(tflow_logs);
        Logger.addLogger(logger);
    }

    private void updateWindowTitle(String newTitle) {
        if (primaryStage != null)
            primaryStage.setTitle(newTitle);
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage ;
    }
}
