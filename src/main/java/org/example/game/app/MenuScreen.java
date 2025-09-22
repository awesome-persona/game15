package org.example.game.app;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuScreen extends VBox {

    public MenuScreen() {
        {
            setAlignment(Pos.CENTER);
            setSpacing(20.0);

            Insets insets = new Insets(20.0, 20.0, 20.0, 20.0);
            setPadding(insets);

            Label title = new Label("Game 15");
            getChildren().add(title);

            setOnKeyPressed(keyEvent -> {
                switch (keyEvent.getCode()) {
                    case Q -> exit();
                    case N -> newGame();
                    case L -> load();
                }
            });
        }

        {
            Button newGame = new Button("New game");
            newGame.setOnAction(e -> newGame());
            getChildren().add(newGame);
        }

        {
            Button loadGame = new Button("Load game");
            loadGame.setOnAction(e -> load());
            getChildren().add(loadGame);
        }

        {
            Button quitGame = new Button("Quit game");
            quitGame.setOnAction(e -> exit());
            getChildren().add(quitGame);
        }
    }

    private void exit() {
        Platform.exit();
    }

    private void load() {
        //FIXME:
        System.out.println("under construction");
        newGame();
    }

    private void newGame() {
        Stage window = (Stage) getScene().getWindow();
        window.setScene(new Scene(new GameScreen(3), 320, 240));
    }

}
