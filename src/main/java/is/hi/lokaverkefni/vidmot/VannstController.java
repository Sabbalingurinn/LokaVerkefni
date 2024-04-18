package is.hi.lokaverkefni.vidmot;

import is.hi.lokaverkefni.vinnsla.Roulette;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class VannstController {
    @FXML
    private Text winText;
    @FXML
    private Label sigur;

    /**
     * Frumstillir og ræsir tímalínu sem breytir litum á sigurtexta á hálfs sekúndu fresti.
     * Birtir einnig sigurupphæð notanda sem er fengin frá Roulette hlut.
     */
    @FXML
    public void initialize() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.5), evt -> switchColor()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        sigur.setText("Til hamingju, þú vannst " + Roulette.getInstance().getMogulegurSigur() + " kr");
    }

    /**
     * Þegar ýtt er á takkan Afram er skipt yfir á Roullette view
     */
    @FXML
    public void onAfram() {
        ViewSwitcher.switchTo(View.ROULLETTE);
    }

    /**
     * Skptir í einhvern lit á 0,5 sek fresti
     */

    private void switchColor() {
        Color color = new Color(Math.random(), Math.random(), Math.random(), 1);
        winText.setFill(color);
    }
}
