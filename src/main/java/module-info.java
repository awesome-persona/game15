module org.example.game {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.game to javafx.fxml;
    exports org.example.game;
    exports org.example.game.app;
    opens org.example.game.app to javafx.fxml;
}