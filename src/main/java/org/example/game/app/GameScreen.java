package org.example.game.app;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class GameScreen extends GridPane{

    private final GameState gameState;

    public GameScreen(GameState gameState) {
        this.gameState = gameState;

        setAlignment(Pos.CENTER);

        setHgap(10);
        setVgap(10);
        setPadding(new Insets(20));

        for (int i = 0; i < gameState.getData().length; i++) {
            for (int j = 0; j < gameState.getData()[i].length; j++) {
                Button button = new Button("" + gameState.getData()[i][j]);
                add(button, i, j);
            }
        }
    }



}
