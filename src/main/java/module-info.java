module is.hi.lokaverkefni {
    requires javafx.controls;
    requires javafx.fxml;


    opens is.hi.lokaverkefni to javafx.fxml;
    exports is.hi.lokaverkefni;
    exports is.hi.lokaverkefni.vidmot;
    opens is.hi.lokaverkefni.vidmot to javafx.fxml;
}
