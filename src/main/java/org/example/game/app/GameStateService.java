package org.example.game.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameStateService implements IGameStateService {

    private static GameStateService instance;

    private GameStateService() {
    }

    public static GameStateService getInstance() {
        if (instance == null) {
            instance = new GameStateService();
        }
        return instance;
    }


    @Override
    public GameState initNewGameState(int size) {
        GameState newGameState = new GameState(size);
        List<Integer> nums = new ArrayList<>(size * size);
        for (int i = 0; i < size * size; i++) {
            nums.add(i);
        }
        Random random = new Random();
        for (int i = 0; i < size * size; i++) {
            newGameState.getData()[i / size][i % size] = nums.remove(random.nextInt(nums.size()));
        }
        return newGameState;
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
        return gameState;
    }
}
