package is.hi.lokaverkefni.vidmot;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ViewSwitcher {

    private static final Map<View, Parent> cache = new HashMap<>();
    private static final Map<View, Object> controllersCache = new HashMap<>();
    private static Scene scene;

    public static void setScene(Scene scene) {
        ViewSwitcher.scene = scene;
    }

    public static Object getController(View view) {
        return controllersCache.get(view);
    }

    public static void switchTo(View view) {
        if (scene == null) {
            System.out.println("Scene has not been set.");
            return;
        }

        try {
            Parent root = cache.get(view);

            if (root == null) {
                System.out.println("Loading from FXML");
                FXMLLoader loader = new FXMLLoader(ViewSwitcher.class.getResource(view.getFileName()));
                root = loader.load(); // Load the FXML file and instantiate the controller

                // Cache the loaded root and controller
                cache.put(view, root);
                controllersCache.put(view, loader.getController());
            } else {
                System.out.println("Loading from cache");
            }

            // Update the scene with the new root
            scene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}