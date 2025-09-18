package org.example.game;

import org.example.game.app.GameState;
import org.example.game.app.GameStateService;

public class Main {

    public static void main(String[] args) {
        GameStateService service = GameStateService.getInstance();
        GameState gameState = service.initNewGameState(4);

        System.out.println(gameState);

        boolean win = service.isWin(gameState);
        System.out.println(win);
    }
}
