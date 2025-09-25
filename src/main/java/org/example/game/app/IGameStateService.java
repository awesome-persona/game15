package org.example.game.app;

public interface IGameStateService {

    GameState getGameState();

    void loadGameState(GameState gameState);

    void initNewGameState(int size);

    boolean isWin(GameState gameState);

    GameState move(GameState gameState, int num);

}
