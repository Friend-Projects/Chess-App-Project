module com.friendprojects.chessapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.friendprojects.chessapp to javafx.fxml;
    exports com.friendprojects.chessapp;
    exports com.friendprojects.chessapp.ui;
    opens com.friendprojects.chessapp.ui to javafx.fxml;
}