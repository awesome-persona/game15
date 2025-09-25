package org.example.game.app.view;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.example.game.app.GameState;
import org.example.game.app.GameStateService;
import org.example.game.app.IGameStateService;

public class GameScreen extends VBox {

    private final IGameStateService gameStateService = GameStateService.getInstance();

    public GameScreen() {

        setAlignment(Pos.CENTER);
        setSpacing(20);
        setPadding(new Insets(20));

        addChildren(false);
    }

    private void addChildren(boolean win) {
        GameMenuBar gameMenuBar = new GameMenuBar();
        getChildren().add(gameMenuBar);

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        setPadding(new Insets(20));

        GameState gameState = gameStateService.getGameState();

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
                gridPane.add(button, j, i);
            }
        }

        getChildren().add(gridPane);
        int length = gameState.getData().length;

        Label gameStatusLabel = new Label("");
        gameStatusLabel.setAlignment(Pos.CENTER);
        gameStatusLabel.setText("You won !!!");
        gameStatusLabel.setVisible(win);
        getChildren().add(gameStatusLabel);


        HBox hBox = new HBox();
        getChildren().add(hBox);


        Button newGame = new Button("New game");
        newGame.setOnAction(e -> gameMenuBar.doOnNewGameClick());
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
