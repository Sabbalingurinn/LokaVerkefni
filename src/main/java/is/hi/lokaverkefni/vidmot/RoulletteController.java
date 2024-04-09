package is.hi.lokaverkefni.vidmot;

import is.hi.lokaverkefni.vinnsla.Roulette;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class RoulletteController {

    private int upphaed;
    private String nafn;
    private int fjoldiVedmal = 0;
    private Roulette roulette = new Roulette();

    @FXML
    private Label fxInnistaeda;
    @FXML
    private TextField fxBettUpphaed;
    @FXML
    private Label fxFjoldiVedmal;
    @FXML
    private GridPane fxBord;
    @FXML
    private Button fxTalanSemDatt;

    private MenuController menuController;
    private LoginController loginController;

    public void onVedja(ActionEvent actionEvent) {
        Button reitur = (Button) actionEvent.getSource();

        if(reitur.getText().equals("R")){
            roulette.baetaVidSvartRauttBet("R");
        } else if(reitur.getText().equals("B")){
            roulette.baetaVidSvartRauttBet("B");
        } else if(reitur.getText().equals("00")){
            roulette.baetaVidToluVedmal(37);
        } else {
            roulette.baetaVidToluVedmal(Integer.parseInt(reitur.getText()));
        }
        reitur.setDisable(true);

        fjoldiVedmal++;
        if(fjoldiVedmal == 3) {
            fxBord.setDisable(true);
        }

        fxFjoldiVedmal.setText((3-fjoldiVedmal) + "/3");

    }

    public void onRoulletteSpin(){
        try {
            if(upphaed - Integer.parseInt(fxBettUpphaed.getText()) > 0) {
                upphaed += roulette.spin(Integer.parseInt(fxBettUpphaed.getText()));
                fxInnistaeda.setText(upphaed + "");
            } else {
                roulette.spin(0);
            }
        } catch (Exception e){
            roulette.spin(0);
        }
        fjoldiVedmal = 0;
        fxBord.setDisable(false);
        for(int i = 0; i < fxBord.getChildren().size(); i++){
            Button reitur = (Button) fxBord.getChildren().get(i);
            reitur.setDisable(false);
        }
        fxFjoldiVedmal.setText("3/3");

        finnaToluSemDatt();
    }

    public void finnaToluSemDatt(){
        int talaSemDatt = roulette.getSidastaTala();

        for(int i = 0; i < fxBord.getChildren().size(); i++){
            Button reitur = (Button) fxBord.getChildren().get(i);
            if(reitur.getText().equals(talaSemDatt+"")){
                fxTalanSemDatt.setStyle("");
                fxTalanSemDatt.setText(reitur.getText());
                fxTalanSemDatt.setTextFill(reitur.getTextFill());
            }
            if(talaSemDatt == 0){
                fxTalanSemDatt.setStyle("-fx-background-color: #06a737;");
            }
            if(talaSemDatt == 37){
                fxTalanSemDatt.setText("00");
                fxTalanSemDatt.setStyle("-fx-background-color: #06a737;");
            }
        }
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
        upphaed = loginController.getUpphaed();
        nafn = loginController.getNafn();
        fxInnistaeda.setText(""+loginController.getUpphaed());
    }

    public void setMenuController(MenuController menuController) {
        menuController.setNafn(loginController.getNafn());
        this.menuController = menuController;
    }

    public void setUpphaed(int upphaed) {
        if(upphaed >= 0) {
            this.upphaed += upphaed;
            System.out.println(this.upphaed);
            fxInnistaeda.setText(this.upphaed + "");
        }
    }

    public void setUpphaedMinus(int upphaed) {
        if(upphaed >= 0 && upphaed <= this.upphaed) {
            this.upphaed -= upphaed;
            System.out.println(this.upphaed);
            fxInnistaeda.setText(this.upphaed + "");
        }
    }

    public String getNafn() {
        return nafn;
    }
}
