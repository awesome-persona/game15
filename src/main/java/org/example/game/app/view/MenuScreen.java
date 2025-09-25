package org.example.game.app.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MenuScreen extends VBox {

    public MenuScreen() {
        GameMenuBar gameMenuBar = new GameMenuBar(true);
        getChildren().add(gameMenuBar);

        {
            setAlignment(Pos.CENTER);
            setSpacing(20.0);

            Insets insets = new Insets(20.0, 20.0, 20.0, 20.0);
            setPadding(insets);

            Label title = new Label("Game 15");
            getChildren().add(title);

            setOnKeyPressed(keyEvent -> {
                switch (keyEvent.getCode()) {
                    case Q -> gameMenuBar.doOnQuitGameClick();
                    case N -> gameMenuBar.doOnNewGameClick();
                    case L -> gameMenuBar.doOnLoadGameClick();
                }
            });
        }

        {
            Button newGame = new Button("New game");
            newGame.setOnAction(e -> gameMenuBar.doOnNewGameClick());
            getChildren().add(newGame);
        }

        {
            Button loadGame = new Button("Load game");
            loadGame.setOnAction(e ->gameMenuBar.doOnLoadGameClick());
            getChildren().add(loadGame);
        }

        {
            Button quitGame = new Button("Quit game");
            quitGame.setOnAction(e -> gameMenuBar.doOnQuitGameClick());
            getChildren().add(quitGame);
        }
    }
}
