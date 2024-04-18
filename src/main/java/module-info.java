module is.hi.lokaverkefni {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens is.hi.lokaverkefni to javafx.fxml;
    exports is.hi.lokaverkefni.vidmot;
    opens is.hi.lokaverkefni.vidmot to javafx.fxml;
}
