module com.friendprojects.chessapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.friendprojects.chessapp to javafx.fxml;
    exports com.friendprojects.chessapp;
    exports com.friendprojects.chessapp.app;
    opens com.friendprojects.chessapp.app to javafx.fxml;
    exports com.friendprojects.chessapp.ui;
    opens com.friendprojects.chessapp.ui to javafx.fxml;
}