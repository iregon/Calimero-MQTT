package calimero; /**
 * @author Alessandro Tornesello
 */

import com.alessandro.calimero.CalimeroServer;
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
