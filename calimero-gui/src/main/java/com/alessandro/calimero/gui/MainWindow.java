package com.alessandro.calimero.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainWindow extends Application {
    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     *
     * <p>
     * NOTE: This method is called on the JavaFX Application Thread.
     * </p>
     *
     * @param primaryStage the primary stage for this application, onto which
     *                     the application scene can be set. The primary stage will be embedded in
     *                     the browser if the application was launched as an applet.
     *                     Applications may create other stages, if needed, but they will not be
     *                     primary stages and will not be embedded in the browser.
     */
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MainWindow.fxml"));
        loader.setControllerFactory((Class<?> type) -> {
            try {
                Object controller = type.newInstance();
                if (controller instanceof MainWindowController) {
                    ((MainWindowController) controller).setPrimaryStage(primaryStage);
                }
                return controller ;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        Parent root = loader.load();
        primaryStage.setScene(new Scene(root, 600, 500));
        primaryStage.setOnCloseRequest(event -> System.exit(0));

        primaryStage.show();
    }
}
