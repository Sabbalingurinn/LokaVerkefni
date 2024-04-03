package is.hi.lokaverkefni;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Lokaverkefni sem verður geggjað kv Sigga og Joi");
    }
}
