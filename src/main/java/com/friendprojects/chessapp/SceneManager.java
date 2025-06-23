package com.friendprojects.chessapp;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.EnumMap;
import java.util.Map;

public class SceneManager {

    public enum AppScene {
        MAIN_MENU("ui/MainMenu.fxml"),
        CHESS("ui/Chess.fxml"),
        GAME_HISTORY("ui/GameHistory.fxml");

        private final String fxmlPath;

        AppScene(String fxmlPath) {
            this.fxmlPath = fxmlPath;
        }

        public String getFxmlPath() {
            return fxmlPath;
        }
    }

    private static Map<AppScene, Scene> sceneMap = new EnumMap<>(AppScene.class);
    private static Stage primaryStage;

    public static void init(Stage stage) {
        primaryStage = stage;
        loadScene(AppScene.MAIN_MENU);
        loadScene(AppScene.CHESS);
        loadScene(AppScene.GAME_HISTORY);
    }

    public static void loadMainMenu() {
        switchToScene(AppScene.MAIN_MENU);
    }

    public static void loadGameScreen() {
        switchToScene(AppScene.CHESS);
    }

    public static void loadReviewScreen() {
        switchToScene(AppScene.GAME_HISTORY);
    }

    private static void loadScene(AppScene appScene) {
        if (!sceneMap.containsKey(appScene)) {
            try {
                FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(appScene.getFxmlPath()));
                Parent root = loader.load();
                sceneMap.put(appScene, new Scene(root, 320, 240));
            } catch (Exception e) {
                System.err.println("Failed to load scene: " + appScene.getFxmlPath());
                e.printStackTrace();
            }
        }
    }

    private static void switchToScene(AppScene appScene) {
        Scene scene = sceneMap.get(appScene);
        primaryStage.setScene(scene);
    }
}
