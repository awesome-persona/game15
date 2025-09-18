package org.example.game.app;

public class GameStateUtils {

    public static GameState set(GameState gameState, int[][] sample) {
        for (int i = 0; i < sample.length; i++) {
            for (int j = 0; j < sample[i].length; j++) {
                gameState.getData()[i][j] = sample[i][j];
            }
        }
        return gameState;
    }
}
