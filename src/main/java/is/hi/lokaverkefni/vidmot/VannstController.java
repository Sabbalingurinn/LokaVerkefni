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

    @FXML
    public void initialize() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.5), evt -> switchColor()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        sigur.setText("Til hamingju, þú vannst " + Roulette.getInstance().getMogulegurSigur() + " kr");
    }

    @FXML
    public void onAfram() {
        ViewSwitcher.switchTo(View.ROULLETTE);
    }

    private void switchColor() {
        Color color = new Color(Math.random(), Math.random(), Math.random(), 1);
        winText.setFill(color);
    }
}
