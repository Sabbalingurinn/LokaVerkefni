package is.hi.lokaverkefni.vidmot;

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        file = new File("roulettevid.mp4");
        media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);

    }

    public void pause(){
        mediaPlayer.pause();
    }

    public void play(){
        mediaPlayer.play();
    }

    public void reset(){
        mediaPlayer.seek(Duration.seconds(0.0));
    }
}
