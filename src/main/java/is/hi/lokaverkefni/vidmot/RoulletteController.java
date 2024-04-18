package is.hi.lokaverkefni.vidmot;

import is.hi.lokaverkefni.vinnsla.Roulette;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.Optional;

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
    @FXML
    private Button fxSpin;

    private MenuController menuController;
    private LoginController loginController;

    /**
     * Meðhöndlar atburð þegar smellt er á hnapp.
     *
     * @param  actionEvent  atburðurinn sem kveikti á aðgerðinni
     */
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
        reitur.getStyleClass().add("casinoChip");

        fjoldiVedmal++;
        if(fjoldiVedmal == 3) {
            fxBord.setDisable(true);
        }

        fxFjoldiVedmal.setText((3-fjoldiVedmal) + "/3");

    }
    /**
     * Framkvæmir "spin" aðgerð á roulette borðinu.
     */
    public void onRoulletteSpin(){
        if (athugaLoglegBetUpphaed()) {
            upphaed = upphaed + roulette.spin(Integer.parseInt(fxBettUpphaed.getText()));
            fxInnistaeda.setText("" + upphaed);
        } else {
            ofHaUpphaed();
        }
        endurStillaAllt();
        finnaToluSemDatt();
    }

    /**
     * Byrtir Alert ef upphæð er ólögleg.
     */
    public void ologlegUpphaed(){
        Alert alert = new Alert(Alert.AlertType.WARNING, "Vinsamlegast skrifaðu inn löglega upphæð.");
        Optional<ButtonType> optional = alert.showAndWait();
        if (optional.isPresent() && optional.get().equals(ButtonType.OK)) { }
    }

    /**
     * Byrtir Alert ef upphæð er of há.
     */
    public void ofHaUpphaed(){
        Alert alert = new Alert(Alert.AlertType.WARNING, "Vinsamlegast skrifaðu inn lægri upphæð.");
        Optional<ButtonType> optional = alert.showAndWait();
        if (optional.isPresent() && optional.get().equals(ButtonType.OK)) { }
    }

    /**
     * Athugar hvort bet upphæð er lögleg.
     *
     * @return True ef bet upphæð er lögleg, false ef ekki.
     */
    public boolean athugaLoglegBetUpphaed(){
        return (upphaed - fjoldiVedmal * Integer.parseInt(fxBettUpphaed.getText()) >= 0);
    }

    /**
     * Endurstilla allt.
     */
    public void endurStillaAllt(){
        fjoldiVedmal = 0;
        fxBord.setDisable(false);
        for(int i = 0; i < fxBord.getChildren().size(); i++){
            Button reitur = (Button) fxBord.getChildren().get(i);
            reitur.setDisable(false);
            reitur.getStyleClass().remove("casinoChip");
        }
        fxFjoldiVedmal.setText("3/3");
        fxBettUpphaed.setText("");
        fxSpin.setDisable(true);
        fxBettUpphaed.setDisable(false);
        roulette.setFjoldiBet(0);
    }

    /**
     * Finnur út og uppfærir hver talan var sem kom út úr síðasta `onRoulletteSpin`.
     */
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

    /**
     * Stillir loginController sem þessi stjórnandi á að nota.
     *
     * @param loginController loginController sem á að nota.
     */
    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
        upphaed = loginController.getUpphaed();
        nafn = loginController.getNafn();
        fxInnistaeda.setText(""+loginController.getUpphaed());
    }

    /**
     * Stillir menuController sem þessi stjórnandi á að nota.
     *
     * @param menuController menuController sem á að nota.
     */
    public void setMenuController(MenuController menuController) {
        menuController.setNafn(loginController.getNafn());
        this.menuController = menuController;
    }

    /**
     * Uppfærir heildarupphæð notanda.
     *
     * @param upphaed Upphæðin sem á að bæta við.
     */
    public void setUpphaed(int upphaed) {
        if(upphaed >= 0) {
            this.upphaed += upphaed;
            System.out.println(this.upphaed);
            fxInnistaeda.setText(this.upphaed + "");
        }
    }

    /**
     * Dregur fjárhæð frá heildarupphæð notanda.
     *
     * @param upphaed Upphæðin sem á að draga frá.
     */
    public void setUpphaedMinus(int upphaed) {
        if(upphaed >= 0 && upphaed <= this.upphaed) {
            this.upphaed -= upphaed;
            System.out.println(this.upphaed);
            fxInnistaeda.setText(this.upphaed + "");
        }
    }

    /**
     * Vistar bet upphæð.
     *
     * @param  actionEvent  atburðurinn sem kveikti á aðgerðinni
     */
    @FXML
    public void onBetILagi(ActionEvent actionEvent) {
        try {
            if (athugaLoglegBetUpphaed()) {
                fxSpin.setDisable(false);
                fxBettUpphaed.setDisable(true);
            } else {
                ofHaUpphaed();
            }
        } catch (Exception e) {
            ologlegUpphaed();
        }
    }

    /**
     * Endurstillir allt.
     *
     * @param  actionEvent  atburðurinn sem kveikti á aðgerðinni
     */
    @FXML
    public void onBetHaettaVid(ActionEvent actionEvent) {
        endurStillaAllt();
    }

    /**
     * Skilar nafni notandans.
     *
     * @return Nafn notandans.
     */
    public String getNafn() {
        return nafn;
    }
}
