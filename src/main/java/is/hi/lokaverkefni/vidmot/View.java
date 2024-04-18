package is.hi.lokaverkefni.vidmot;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public enum View {
    LOGIN("/is/hi/lokaverkefni/login-view.fxml"),
    ROULLETTE("/is/hi/lokaverkefni/Roulette-view.fxml"),
    VANNST("/is/hi/lokaverkefni/vannst-view.fxml"),

    MEDIA("/is/hi/lokaverkefni/rouletteMedia-view.fxml");




    private String fileName;

    View(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
