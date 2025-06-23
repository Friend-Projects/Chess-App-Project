package com.friendprojects.chessapp;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        SceneManager.init(stage);
        SceneManager.loadMainMenu();
        stage.setTitle("Chess App");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}