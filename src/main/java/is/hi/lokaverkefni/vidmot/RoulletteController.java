package is.hi.lokaverkefni.vidmot;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class RoulletteController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Lokaverkefni sem verður geggjað kv Sigga og Joi");
    }
}
