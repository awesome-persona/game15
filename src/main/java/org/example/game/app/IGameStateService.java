package org.example.game.app;

public interface IGameStateService {

    GameState initNewGameState(int size);

    boolean isWin(GameState gameState);

    GameState move(GameState gameState, int num);

}
