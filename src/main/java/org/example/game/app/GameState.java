package org.example.game.app;

import java.util.Arrays;

public class GameState {

    // X x Y;
    private final int size;
    private final int[][] data;

    public GameState(int size) {
        this.size = size;
        this.data = new int[size][size];
    }

    public int getSize() {
        return size;
    }

    public int[][] getData() {
        return data;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                builder.append(String.format(" %4s", data[i][j] == 0 ? "[]" : data[i][j]));
            }
            builder.append("\n");
        }
        builder.append("\n");

        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        GameState gameState = (GameState) o;
        return size == gameState.size && Arrays.deepEquals(data, gameState.data);
    }

    @Override
    public int hashCode() {
        int result = size;
        result = 31 * result + Arrays.deepHashCode(data);
        return result;
    }
}
