package is.hi.lokaverkefni.vidmot;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class RoulletteController {
    @FXML
    private Label welcomeText;

    private int upphaed;
    private String nafn;

    @FXML
    private Label fxUpphaed;
    @FXML
    private Label fxNafn;

    private LoginController loginController;

    public void initialize(){

    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Lokaverkefni sem verður geggjað kv Sigga og Joi");
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
        fxUpphaed.setText(""+loginController.getUpphaed());
        fxNafn.setText(loginController.getNafn());
    }
}
