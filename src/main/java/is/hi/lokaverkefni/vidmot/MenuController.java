package is.hi.lokaverkefni.vidmot;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;

import java.io.IOException;
import java.util.Optional;

public class MenuController {

    private RoulletteController roulletteController;

    public static final String VILTU_HAETTA = "Viltu hætta? ";
    public static final String INFO= "Þetta forrit er Rouletta sem þú getur spilað með gervi pening" ;


    public void onHaetta(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.WARNING, VILTU_HAETTA);
        Optional<ButtonType> optional = alert.showAndWait();
        if (optional.isPresent() && optional.get().equals(ButtonType.OK)) {
            System.exit(0);
        }
    }

    public void onBanki(){
        try {
            FXMLLoader.load(getClass().getResource("Bank-Dialog.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        TextInputDialog banki = new TextInputDialog();

        banki.setTitle("Banki");

        banki.setHeaderText("Hversu mikinn pening viltu leggja inn");

        banki.setContentText("Skrifaðu hér:");

        Optional<String> upphaed = banki.showAndWait();

        String peningur = upphaed.get();


        banki.close();

    }


    public void onInfo(ActionEvent actionEvent){
        Alert alert = new Alert(Alert.AlertType.INFORMATION, INFO);
        alert.showAndWait();
    }

    public void setRoulletteController(RoulletteController roulletteController){
        this.roulletteController = roulletteController;

    }



}

