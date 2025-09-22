package org.example.game.app;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class GameScreen extends GridPane {

    private IGameStateService gameStateService = GameStateService.getInstance();
    private final GameState gameState;
    public GameScreen(int size) {
        this.gameState = gameStateService.initNewGameState(size);

        setAlignment(Pos.CENTER);

        setHgap(10);
        setVgap(10);
        setPadding(new Insets(20));

        addChildren(false);
    }

    private void addChildren(boolean win) {
        for (int i = 0; i < gameState.getData().length; i++) {
            for (int j = 0; j < gameState.getData()[i].length; j++) {
                int num = gameState.getData()[i][j];
                Button button = new Button("" + num);//num == 0 ? "" :
                button.setMinWidth(35);
                button.setMinHeight(35);
                button.setAlignment(Pos.CENTER);
                button.setDisable(win);
                button.setOnAction(e -> {
                    gameStateService.move(gameState, num);
                    boolean isWin = gameStateService.isWin(gameState);
                    refresh(isWin);
                });
                if (num == 0) {
                    button.setVisible(false);
                }
                add(button, j, i);
            }
        }

        int length = gameState.getData().length;

        Label gameStatusLabel = new Label("");
        gameStatusLabel.setAlignment(Pos.CENTER);
        gameStatusLabel.setText("You won !!!");
        gameStatusLabel.setVisible(win);
        add(gameStatusLabel, 0, length, length, 1);


        HBox hBox = new HBox();
        add(hBox, 0, length + 1, length, 1);


        Button newGame = new Button("New game");
        newGame.setOnAction(e -> {
            Stage window = (Stage) getScene().getWindow();

            window.setScene(new Scene(new GameScreen(3), 320, 240));
        });
        newGame.setVisible(win);
        hBox.getChildren().add(newGame);

        Button quitGame = new Button("Quit game");
        quitGame.setOnAction(e -> Platform.exit());
        quitGame.setVisible(win);

        hBox.getChildren().add(quitGame);
    }

    private void refresh(boolean win) {
        getChildren().clear();
        addChildren(win);
    }

}
