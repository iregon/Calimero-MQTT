package gui;

import com.alessadnro.calimero.gui.MainWindow;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class MainWindowTest {

    MainWindow mainWindow = new MainWindow();

    @Test
    void getParent() throws IOException {
        assertNotNull(mainWindow.getParent());
    }
}