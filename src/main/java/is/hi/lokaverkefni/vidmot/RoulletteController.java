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

    private MenuController menuController;
    private LoginController loginController;

    public void initialize(){

    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Lokaverkefni sem verður geggjað kv Sigga og Joi");
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
        upphaed = loginController.getUpphaed();
        nafn = loginController.getNafn();
        fxUpphaed.setText(""+loginController.getUpphaed());
        fxNafn.setText(loginController.getNafn());
    }

    public void setMenuController(MenuController menuController) {
        menuController.setNafn(loginController.getNafn());
        this.menuController = menuController;
    }

    public void setUpphaed(int upphaed) {
        if(upphaed >= 0) {
            this.upphaed += upphaed;
            System.out.println(this.upphaed);
            fxUpphaed.setText(this.upphaed + "");
        }
    }

    public void setUpphaedMinus(int upphaed) {
        if(upphaed >= 0 && upphaed <= this.upphaed) {
            this.upphaed -= upphaed;
            System.out.println(this.upphaed);
            fxUpphaed.setText(this.upphaed + "");
        }
    }

    public String getNafn() {
        return nafn;
    }
}
