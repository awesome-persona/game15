package org.example.game;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) {

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(20.0);

        Insets insets = new Insets(20.0, 20.0, 20.0, 20.0);
        vBox.setPadding(insets);

        Label welcomeText = new Label();
        vBox.getChildren().add(welcomeText);
        Button button = new Button("Start");
        button.setOnAction(actionEvent -> {
            welcomeText.setText("Welcome to JavaFX Application!");
        });
        vBox.getChildren().add(button);

        Scene scene = new Scene(vBox, 320, 240);

        stage.setTitle("Game 15");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}