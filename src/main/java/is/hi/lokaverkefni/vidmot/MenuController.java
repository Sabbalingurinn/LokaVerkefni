package is.hi.lokaverkefni.vidmot;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class MenuController {

    private RoulletteController roulletteController;
    private String nafn;

    @FXML
    private MenuItem menuItem;

    public static final String VILTU_HAETTA = "Viltu hætta? ";
    public static final String INFO= "Þetta forrit er Rouletta sem þú getur spilað með gervi pening" ;

    public void initialize(){

    }

    public void onHaetta(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.WARNING, VILTU_HAETTA);
        Optional<ButtonType> optional = alert.showAndWait();
        if (optional.isPresent() && optional.get().equals(ButtonType.OK)) {
            System.exit(0);
        }
    }

    public void onBanki(){
        /**try {
            FXMLLoader.load(getClass().getResource("Bank-Dialog.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
         **/

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

    public void onLeikmadur(){
        roulletteController = (RoulletteController) ViewSwitcher.getController(View.ROULLETTE);
        roulletteController.setMenuController(this);
    }


    public void onInfo(ActionEvent actionEvent){
        Alert alert = new Alert(Alert.AlertType.INFORMATION, INFO);
        alert.showAndWait();
    }

    public void setRoulletteController(RoulletteController roulletteController){
        setNafn(roulletteController.getNafn());
        this.roulletteController = roulletteController;
    }

    public void setNafn(String nafn){
        this.nafn = nafn;
        menuItem.setText(nafn);
    }



}

