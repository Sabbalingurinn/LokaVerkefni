package is.hi.lokaverkefni.vidmot;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Optional;

public class MenuController {

    private RoulletteController roulletteController;
    private String nafn;

    private Image slokkvaHnappurMynd = new Image("file:src/main/resources/is/hi/lokaverkefni/css/media/offTakki.png");

    @FXML
    private ImageView fxSlokkvaHnappur;

    /**
     * Frumstillir mynd í viðmóti við upphaf.
     */
    public void initialize() {
        fxSlokkvaHnappur.setImage(slokkvaHnappurMynd);
    }

    public static final String VILTU_HAETTA = "Viltu hætta? ";
    public static final String INFO= "Þetta forrit er Rouletta sem þú getur spilað með gervi pening" ;

    /**
     * Meðhöndlar viðburð þegar notandi velur að hætta í forritinu.
     * Birtir viðvörunarglugga og lokar forritinu ef notandi staðfestir.
     *
     * @param actionEvent Atburðurinn sem kveikti á aðgerðinni.
     */
    public void onHaetta(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.WARNING, VILTU_HAETTA);
        Optional<ButtonType> optional = alert.showAndWait();
        if (optional.isPresent() && optional.get().equals(ButtonType.OK)) {
            System.exit(0);
        }
    }

    /**
     * Opnar dialogglugga þar sem notandi getur sett inn pening.
     * Uppfærir upphæð í tengdum RouletteController.
     */
    public void onBanki(){

        TextInputDialog banki = new TextInputDialog();

        banki.setTitle("Banki");

        banki.setHeaderText("Hversu mikinn pening viltu leggja inn");

        banki.setContentText("Skrifaðu hér:");

        Optional<String> upphaed = banki.showAndWait();

        String peningur = upphaed.get();

        roulletteController = (RoulletteController) ViewSwitcher.getController(View.ROULLETTE);
        roulletteController.setMenuController(this);
        roulletteController.setUpphaed(Integer.parseInt(peningur));

        banki.close();

    }

    /**
     * Opnar dialogglugga þar sem notandi getur tekið út pening.
     * Uppfærir upphæð í tengdum RouletteController.
     */
    public void onBankiUt(){
        TextInputDialog banki = new TextInputDialog();

        banki.setTitle("Banki");

        banki.setHeaderText("Hversu mikinn pening viltu taka út?");

        banki.setContentText("Skrifaðu hér:");

        Optional<String> upphaed = banki.showAndWait();

        String peningur = upphaed.get();

        roulletteController = (RoulletteController) ViewSwitcher.getController(View.ROULLETTE);
        roulletteController.setMenuController(this);
        roulletteController.setUpphaedMinus(Integer.parseInt(peningur));

        banki.close();

    }

    /**
     * Birtir notendaprofíl í upplýsingaglugga.
     */
    public void onProfill(){
        roulletteController = (RoulletteController) ViewSwitcher.getController(View.ROULLETTE);
        roulletteController.setMenuController(this);
        Alert alert = new Alert(Alert.AlertType.INFORMATION, nafn);
        alert.showAndWait();
    }

    /**
     * Birtir upplýsingar um forritið í upplýsingaglugga.
     *
     * @param actionEvent Atburðurinn sem kveikti á aðgerðinni.
     */
    public void onInfo(ActionEvent actionEvent){
        Alert alert = new Alert(Alert.AlertType.INFORMATION, INFO);
        alert.showAndWait();
    }

    /**
     * Stillir tengdan RouletteController og uppfærir nafn notanda.
     *
     * @param roulletteController Tengdur RouletteController.
     */
    public void setRoulletteController(RoulletteController roulletteController){
        setNafn(roulletteController.getNafn());
        this.roulletteController = roulletteController;
    }

    /**
     * Stillir nafn notanda.
     *
     * @param nafn Nafn notanda.
     */
    public void setNafn(String nafn){
        this.nafn = nafn;
    }



}

