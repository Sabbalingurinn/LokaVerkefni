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
    private MenuController menuController;

    /**
     * Upphafsstillir stjórnandann og býr til tengsl á milli textareita og hnappsins 'Í lagi'.
     * Hnappurinn er óvirkur ef textareitir fyrir nafn eða upphæð eru auðir.
     */
    public void initialize() {
        fxILagi.disableProperty().bind(fxNafn.textProperty().isEmpty().or(fxUpphaed.textProperty().isEmpty()));
    }

    /**
     * Meðhöndlar atburð þegar ýtt er á 'Í lagi' hnappinn.
     * Skiptir um skjá yfir í Roulette viðmótið og stillir upp tengslum við þann stjórnanda.
     */
    @FXML
    protected void onILagi() {
        ViewSwitcher.switchTo(View.ROULLETTE);
        roulletteController = (RoulletteController) ViewSwitcher.getController(View.ROULLETTE);
        roulletteController.setLoginController(this);

    }

    /**
     * Meðhöndlar atburð þegar ýtt er á 'Hætta við' hnappinn.
     * Lokar forritinu.
     */
    @FXML
    protected void onHaettaVid() {
        System.exit(0);
    }

    /**
     * Nær í notandanafnið úr textareit.
     *
     * @return Notandanafn sem er skráð í textareit.
     */
    public String getNafn(){
        return fxNafn.getText();
    }

    /**
     * Nær í upphæðina úr textareit og umreiknar hana í heiltölu.
     * Ef textareitinn inniheldur ekki gilda tölu, skilar aðferðin 1000 sem sjálfgefna upphæð.
     *
     * @return Upphæð sem notandi hefur skráð, eða 1000 ef inntak er ógilt.
     */
    public int getUpphaed(){
        try {
            int upphaed = Integer.parseInt(fxUpphaed.getText());
            return upphaed;
        } catch (Exception exception) {
            return 1000;
        }
    }
}
