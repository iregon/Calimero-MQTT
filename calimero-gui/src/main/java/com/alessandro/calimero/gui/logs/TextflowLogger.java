package com.alessandro.calimero.gui.logs;

import com.alessandro.logger.interfaces.ILoggerHandler;
import javafx.application.Platform;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.text.MessageFormat;

public class TextflowLogger implements ILoggerHandler {

    private TextFlow textflow;

    public TextflowLogger(TextFlow textflow) {
        this.textflow = textflow;
    }

    @Override
    public void info(String s) {
        appendText(s);
    }

    private void appendText(String msg) {
        Text t = new Text(MessageFormat.format("{0}\n", msg));

        Platform.runLater(() -> textflow.getChildren().add(t));
    }
}
