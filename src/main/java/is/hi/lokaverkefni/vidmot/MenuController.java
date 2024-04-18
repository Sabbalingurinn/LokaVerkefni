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
    private Image hjalpHnappurMynd = new Image("file:src/main/resources/is/hi/lokaverkefni/css/media/info-logo.png");
    private Image leikmadurHnappurMynd = new Image("file:src/main/resources/is/hi/lokaverkefni/css/media/User_icon.png");
    private Image bankiHnappurMynd = new Image("file:src/main/resources/is/hi/lokaverkefni/css/media/bank-icon.png");

    @FXML
    private ImageView fxSlokkvaHnappur;
    @FXML
    private ImageView fxHjalpHnappur;
    @FXML
    private ImageView fxBankiHnappur;
    @FXML
    private ImageView fxLeikmadurHnappur;

    /**
     * Frumstillir mynd í viðmóti við upphaf.
     */
    public void initialize() {
        fxSlokkvaHnappur.setImage(slokkvaHnappurMynd);
        fxHjalpHnappur.setImage(hjalpHnappurMynd);
        fxBankiHnappur.setImage(bankiHnappurMynd);
        fxLeikmadurHnappur.setImage(leikmadurHnappurMynd);
    }

    public static final String VILTU_HAETTA = "Viltu hætta? ";
    public static final String INFO= "Ársgerð: 2024 \n" +
                                    "Höfundar: Sævar Breki, Jóhannes og Sigríður Soffía \n" +
                                    "\n" +
                                    "Þetta forrit er einföld útgáfa af fjárhættuspilinu Roulette. Leikmaðurinn, þú, velur 1-3 reiti til að veðja á. Hægt er að velja reiti sem innihalda tölu, þar á meðal grænu reitina 0 og 00, auk þess er hægt að velja rauða reitinn og svarta reitinn. \n" +
                                    "\n" +
                                    "Ef valið er rauða eða svarta reitinn er leikmaður að veðja á að þegar borðinu er snúið verði útkoman rauð tala eða svört tala. Ef valið eru reit sem inniheldur tölu er leikmaður að veðja á að þegar borðinu er snúið verð útkoman sú tala. \n" +
                                    "\n" +
                                    "Ef leikamaðurinn veðjar á tölu og sú tala lendir fær leikmaðurinn til baka upphæðina sem hann lagði undir margfaldað með 35. Ef leikmaðurinn veðjar á að talan verði svört eða rauð og það reynist satt mun leikmaðurinn fá til baka upphæðina sem hann lagði undir margfaldað með 2. \n" +
                                    "\n" +
                                    "Til þess að velja reit sem leikmaður vill veðja á skal hann einfaldlega smella á þann reit, leikmaður get valið samtalst þrjá reiti. Næst skal leikmaður skrifa inn upphæð sem hann er tilbúin að veðja og smella á reitinn `Í lagi`, ef hann vill hætta við skal hann smella á reitinn `Hætta við`. Næst skal leikmaðurinn smella á Roulette hjólið til að snúa hjólinu og vona það allra besta." ;

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

