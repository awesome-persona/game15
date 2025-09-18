package org.example.game.app;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameStateServiceTest {

    private final GameStateService service = GameStateService.getInstance();

    @ParameterizedTest
    @ValueSource(ints = {3, 4, 5, 6, 7})
    public void testInitNewGameState(int n) {
        GameState gameState = service.initNewGameState(n);
        assertEquals(n, gameState.getSize(), "game board size is not valid");
        assertEquals(n, gameState.getData().length, "game board size is not valid");
        for (int i = 0; i < gameState.getData().length; i++) {
            assertEquals(n, gameState.getData()[i].length, "game board size is not valid");
        }

        Set<Integer> nums = new TreeSet<>();
        for (int[] arr : gameState.getData()) {
            for (int val : arr) {
                nums.add(val);
            }
        }

        assertEquals(n * n, nums.size());
        assertEquals(n * n - 1, nums.stream().mapToInt(Integer::intValue).max().orElseThrow());
        assertEquals(0, nums.stream().mapToInt(Integer::intValue).min().orElseThrow());
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 4, 5})
    public void testIsWinTrue(int n) {
        GameState gameState = new GameState(n);
        assertFalse(service.isWin(gameState));
        for (int i = 0, k = 1; i < n; i++) {
            assertFalse(service.isWin(gameState));
            for (int j = 0; j < n; j++) {
                gameState.getData()[i][j] = k++;
            }
        }

        assertFalse(service.isWin(gameState), "expecting game is not finished: \n" + gameState);

        gameState.getData()[n - 1][n - 1] = 0;

        assertTrue(service.isWin(gameState), "expecting win: \n" + gameState);
    }

    @Test
    public void testMove1Tile() {
        GameState gameState = new GameState(3);
        int[][] sample =
                {
                        {1, 4, 5},
                        {3, 2, 8},
                        {7, 0, 6}
                };

        GameStateUtils.set(gameState, sample);

        GameState actual = service.move(gameState, 6);


        GameState expected = new GameState(3);
        sample = new int[][]
                {
                        {1, 4, 5},
                        {3, 2, 8},
                        {7, 6, 0}
                };
        GameStateUtils.set(expected, sample);
        assertEquals(expected, actual, "Move tile 6\nExpected:\n" + expected + " Actual:\n" + actual);

        actual = service.move(gameState, 8);
        sample = new int[][]
                {
                        {1, 4, 5},
                        {3, 2, 0},
                        {7, 6, 8}
                };
        GameStateUtils.set(expected, sample);
        assertEquals(expected, actual, "Move tile 8\nExpected:\n" + expected + " Actual:\n" + actual);

        actual = service.move(gameState, 2);
        sample = new int[][]
                {
                        {1, 4, 5},
                        {3, 0, 2},
                        {7, 6, 8}
                };
        GameStateUtils.set(expected, sample);
        assertEquals(expected, actual, "Move tile 2\nExpected:\n" + expected + " Actual:\n" + actual);

        actual = service.move(gameState, 6);
        sample = new int[][]
                {
                        {1, 4, 5},
                        {3, 2, 0},
                        {7, 6, 8}
                };
        GameStateUtils.set(expected, sample);
        assertEquals(expected, actual, "Move tile 6\nExpected:\n" + expected + " Actual:\n" + actual);
    }

    @Test
    public void testMoveMultipleTiles() {
        GameState gameState = new GameState(3);
        int[][] sample =
                {
                        {1, 4, 5},
                        {3, 2, 8},
                        {7, 0, 6}
                };

        GameStateUtils.set(gameState, sample);

        GameState actual = service.move(gameState, 6);


        GameState expected = new GameState(3);
        sample = new int[][]
                {
                        {1, 4, 5},
                        {3, 2, 8},
                        {7, 6, 0}
                };
        GameStateUtils.set(expected, sample);
        assertEquals(expected, actual, "Move tile 6\nExpected:\n" + expected + " Actual:\n" + actual);

        actual = service.move(gameState, 5);
        sample = new int[][]
                {
                        {1, 4, 0},
                        {3, 2, 5},
                        {7, 6, 8}
                };
        GameStateUtils.set(expected, sample);
        assertEquals(expected, actual, "Move tile 5\nExpected:\n" + expected + " Actual:\n" + actual);

        actual = service.move(gameState, 1);
        sample = new int[][]
                {
                        {0, 1, 4},
                        {3, 2, 5},
                        {7, 6, 8}
                };
        GameStateUtils.set(expected, sample);
        assertEquals(expected, actual, "Move tile 1\nExpected:\n" + expected + " Actual:\n" + actual);

        actual = service.move(gameState, 7);
        sample = new int[][]
                {
                        {3, 1, 4},
                        {7, 2, 5},
                        {0, 6, 8}
                };
        GameStateUtils.set(expected, sample);
        assertEquals(expected, actual, "Move tile 7\nExpected:\n" + expected + " Actual:\n" + actual);
    }
}
