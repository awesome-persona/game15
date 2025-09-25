package org.example.game;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.game.app.view.GameMenuBar;
import org.example.game.app.view.MenuScreen;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new MenuScreen(), 320, 240);
        stage.setTitle("Game 15");
        stage.setScene(scene);
        stage.show();

        scene.getWindow().setOnCloseRequest(e -> new GameMenuBar().doOnQuitGameClick());
    }

    public static void main(String[] args) {
        launch();
    }
}