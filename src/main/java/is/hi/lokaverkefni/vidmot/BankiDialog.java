package is.hi.lokaverkefni.vidmot;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextInputDialog;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class BankiDialog implements Initializable {

    public int pening;

    /**
     * Upphafsstillir dialog glugga fyrir innleggingu peninga.
     * Notar FXMLLoader til að hlaða viðmótsútlit frá 'Bank-Dialog.fxml'.
     *
     * @param url             URL hlutur sem bendir á staðsetningu fyrir 'initialize' (not used directly).
     * @param resourceBundle  ResourceBundle hlutur sem inniheldur staðsetningar-eða locale-sértæk gögn.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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

        pening = Integer.parseInt(peningur);


        banki.close();

    }

    /**
     * Skilar fjölda peninga sem notandi ákvað að leggja inn.
     *
     * @return Fjöldi peninga.
     */
    public int getPening(){
        return pening;
    }


}

