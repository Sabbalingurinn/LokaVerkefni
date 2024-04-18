package is.hi.lokaverkefni.vidmot;

import is.hi.lokaverkefni.vinnsla.Roulette;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;



public class RouletteMediaController implements Initializable {

    private File file;

    private Media media;

    private MediaPlayer mediaPlayer;
    @FXML
    private MediaView mediaView;



    /**
     * Frumstilla vídeóspilarann og hefja afspilun þegar klasi er búinn til.
     * @param url URL hlutur sem vísar í staðsetningu sem klasi er frumstilltur frá, ekki notaður í þessu samhengi.
     * @param resourceBundle ResourceBundle hlutur til að gera alþjóðlegan stuðning, ekki notaður í þessu samhengi.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupMediaPlayer();
        play();
    }

    /**
     * Uppsetning og tilbúningur af MediaPlayer. Þetta inniheldur útbúnað af vídeóslóð,
     * tilbúningur af MediaPlayer, og skilgreining atburðarhöndlara fyrir klárað vídeó.
     */

    public void setupMediaPlayer() {
        try {
            String videoPath = new File("src/main/resources/is/hi/lokaverkefni/css/media/roulettevid.mp4").toURI().toString();
            String uncachedVideoPath = videoPath + "?ignoreCache=" + System.currentTimeMillis();
            Media media = new Media(uncachedVideoPath);
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setOnReady(() -> {
                System.out.println("MediaPlayer is ready.");
                mediaPlayer.play();
            });
            mediaPlayer.setOnEndOfMedia(() -> {
                System.out.println("Video playback ended.");
                mediaPlayer.seek(Duration.ZERO);
                mediaPlayer.pause();
                ViewSwitcher.switchTo(View.ROULLETTE);
                athugaHvortVann();
            });
            mediaView.setMediaPlayer(mediaPlayer);
        } catch (Exception e) {
            System.err.println("Error initializing MediaPlayer: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Skiptir um senu ef notandi vann.
     */
    public void athugaHvortVann() {
        if (Roulette.getInstance().getMogulegurSigur() > 0){
            ViewSwitcher.switchTo(View.VANNST);
        }
    }

    /**
     * Spila vídeó ef MediaPlayer er tilbúinn. Þetta felur í sér að stoppa og endurstilla spilarann áður en spilun hefst.
     */
    public void play() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.seek(Duration.ZERO);
            mediaPlayer.play();
        }
    }
    /**
     * Endurstillir og spilar vídeó á nýjan leik.
     */
    public void resetAndPlay() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.seek(Duration.ZERO);
            mediaPlayer.play();
        }
    }

    /**
     * Endurstillir MediaPlayer með því að farga núverandi spilara og búa til nýjan.
     */
    public void resetMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.dispose();
        }
        setupMediaPlayer();

    }
    /**
     * Atburðahandler fyrir "sleppa" aðgerðina. Skiptir um útlit og endurstillir vídeóspilarann.
     * @param actionEvent Atburðarupplýsingar fyrir aðgerðina, ekki notað í þessu samhengi.
     */

    public void onSkip(ActionEvent actionEvent){
        ViewSwitcher.switchTo(View.ROULLETTE);
        athugaHvortVann();
        resetMediaPlayer();
    }

}
