package org.example.game.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameStateService implements IGameStateService {

    private static GameStateService instance;

    private GameState gameState;

    private GameStateService() {
    }

    public static GameStateService getInstance() {
        if (instance == null) {
            instance = new GameStateService();
        }
        return instance;
    }


    @Override
    public GameState getGameState() {
        return gameState;
    }

    @Override
    public void loadGameState(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public void initNewGameState(int size) {
        GameState newGameState = new GameState(size);
        List<Integer> nums = new ArrayList<>(size * size);
        for (int i = 0; i < size * size; i++) {
            nums.add(i);
        }
        Random random = new Random();
        for (int i = 0; i < size * size; i++) {
            newGameState.getData()[i / size][i % size] = nums.remove(random.nextInt(nums.size()));
        }
        gameState = newGameState;
    }

    @Override
    public boolean isWin(GameState gameState) {
        boolean isWin = true;
        int n = gameState.getSize();
        for (int i = 0, num = 1; i < n && isWin; i++) {
            for (int j = 0; j < n && isWin && num < n * n; j++, num++) {
                if (gameState.getData()[i][j] != num) {
                    isWin = false;
                }
            }
        }

        return isWin && gameState.getData()[n - 1][n - 1] == 0;
    }

    @Override
    public GameState move(GameState gameState, int num) {
        int x0 = -1;
        int y0 = -1;
        int x1 = -1;
        int y1 = -1;
        for (int i = 0; i < gameState.getSize(); i++) {
            for (int j = 0; j < gameState.getSize(); j++) {
                if (gameState.getData()[i][j] == 0) {
                    x0 = i;
                    y0 = j;
                }

                if (gameState.getData()[i][j] == num) {
                    x1 = i;
                    y1 = j;
                }
            }
        }

        if (x0 == x1) {
            for (int j = y0; j < y1; j++) {
                int temp = gameState.getData()[x0][j];
                gameState.getData()[x0][j] = gameState.getData()[x0][j + 1];
                gameState.getData()[x0][j + 1] = temp;
            }

            for (int j = y0; j > y1; j--) {
                int temp = gameState.getData()[x0][j];
                gameState.getData()[x0][j] = gameState.getData()[x0][j - 1];
                gameState.getData()[x0][j - 1] = temp;
            }
        }

        if (y0 == y1) {
            for (int j = x0; j < x1; j++) {
                int temp = gameState.getData()[j][y0];
                gameState.getData()[j][y0] = gameState.getData()[j + 1][y0];
                gameState.getData()[j + 1][y0] = temp;
            }

            for (int j = x0; j > x1; j--) {
                int temp = gameState.getData()[j][y0];
                gameState.getData()[j][y0] = gameState.getData()[j - 1][y0];
                gameState.getData()[j - 1][y0] = temp;
            }
        }

        return gameState;
    }
}
