module com.friendprojects.chessapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.friendprojects.chessapp to javafx.fxml;
    exports com.friendprojects.chessapp;
}