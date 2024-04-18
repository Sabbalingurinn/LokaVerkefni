package is.hi.lokaverkefni.vidmot;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public enum View {
    LOGIN("/is/hi/lokaverkefni/login-view.fxml"),
    ROULLETTE("/is/hi/lokaverkefni/Roulette-view.fxml"),




    private String fileName;

    View(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
