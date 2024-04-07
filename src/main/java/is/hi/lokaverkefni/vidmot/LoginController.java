package is.hi.lokaverkefni.vidmot;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    private TextField fxNafn;
    @FXML
    private TextField fxUpphaed;
    @FXML
    private Button fxILagi;

    private RoulletteController roulletteController;

    public void initialize() {
        fxILagi.disableProperty().bind(fxNafn.textProperty().isEmpty().or(fxUpphaed.textProperty().isEmpty()));
    }

    @FXML
    protected void onILagi() {
        ViewSwitcher.switchTo(View.ROULLETTE);
        roulletteController = (RoulletteController) ViewSwitcher.getController(View.ROULLETTE);
        roulletteController.setLoginController(this);
    }

    @FXML
    protected void onHaettaVid() {
        System.exit(0);
    }

    public String getNafn(){
        return fxNafn.getText();
    }

    public int getUpphaed(){
        try {
            int upphaed = Integer.parseInt(fxUpphaed.getText());
            return upphaed;
        } catch (Exception exception) {
            return 1000;
        }
    }
}
