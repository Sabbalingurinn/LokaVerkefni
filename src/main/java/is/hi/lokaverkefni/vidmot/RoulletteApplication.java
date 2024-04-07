package is.hi.lokaverkefni.vidmot;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class RoulletteApplication extends Application {
    @Override
    public void start(Stage stage) {
        // Búin til sena með einhverjum viðmótshlut
        var scene = new Scene(new Pane());

        // Setjum senuna sem núverandi senu
        ViewSwitcher.setScene(scene);

        // skiptum yfir í viðmótstré fyrir LOGIN
        ViewSwitcher.switchTo(View.LOGIN);

        // tengjum senuna við gluggann
        stage.setScene(scene);

        // sýnum glugggann
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
