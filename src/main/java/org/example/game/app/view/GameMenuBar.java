package org.example.game.app.view;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.example.game.app.GameState;
import org.example.game.app.GameStateService;
import org.example.game.app.IGameStateService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class GameMenuBar extends MenuBar {

    IGameStateService gameStateService = GameStateService.getInstance();

    public GameMenuBar() {
        this(false);
    }

    public GameMenuBar(boolean disableSave) {
        Menu menu = new Menu("Game 15");
        getMenus().add(menu);

        MenuItem menuItemN = new MenuItem("new (n)");
        menuItemN.setOnAction(e -> doOnNewGameClick());
        MenuItem menuItemL = new MenuItem("load (l)");
        menuItemL.setOnAction(e -> doOnLoadGameClick());
        MenuItem menuItemS = new MenuItem("save (s)");
        menuItemS.setDisable(disableSave);
        menuItemS.setOnAction(e -> doOnSaveGameClick());
        MenuItem menuItemQ = new MenuItem("quit (q)");
        menuItemQ.setOnAction(e -> doOnQuitGameClick());

        menu.getItems().add(menuItemN);
        menu.getItems().add(menuItemL);
        menu.getItems().add(menuItemS);
        menu.getItems().add(menuItemQ);
    }

    public void doOnNewGameClick() {
        Stage window = (Stage) getScene().getWindow();
        gameStateService.initNewGameState(3);
        window.setScene(new Scene(new GameScreen(), 320, 240));
    }

    public void doOnLoadGameClick() {
        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("load game");
        fileChooser.setInitialFileName("save0.data");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        Stage window = (Stage) getScene().getWindow();
        File file = fileChooser.showOpenDialog(window);

        try (var fis = new FileInputStream(file);
             var ois = new ObjectInputStream(fis)){
            GameState gameState = (GameState) ois.readObject();
            gameStateService.loadGameState(gameState);
        } catch (Exception e) {
            e.printStackTrace();
        }

        window.setScene(new Scene(new GameScreen(), 320, 240));
    }


    public void doOnSaveGameClick() {
        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("save game");
        fileChooser.setInitialFileName("save0.data");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        File file = fileChooser.showSaveDialog(getScene().getWindow());

        try (var fos = new FileOutputStream(file);
             var oos = new ObjectOutputStream(fos)){
            oos.writeObject(gameStateService.getGameState());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doOnQuitGameClick() {
        Platform.exit();
    }

}
