package sample.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import sample.logic.GameController;

import java.util.ArrayList;
import java.util.List;

public class GameLayout extends HBox {

    GameController gameController;

    public GameLayout(GameCanvas gameCanvas, GameController gameController) {
        this.gameController = gameController;
        getChildren().add(gameCanvas);
        getChildren().add(rightSideMenu());
        setPadding(new Insets(0, 50, 0, 0));
        setSpacing(50.0);
    }

    private VBox rightSideMenu() {
        List<Button> buttons = new ArrayList<>();
        buttons.add(restartGameButton());
        buttons.forEach(button -> button.setMaxSize(100.0, 30.0));
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(20, 0, 20, 0));
        vbox.setSpacing(20.0);
        vbox.setAlignment(Pos.BASELINE_CENTER);
        vbox.getChildren().addAll(buttons);
        return vbox;
    }

//    private Button startGameButton() {
//        Button startGameButton = new Button("Start");
//        startGameButton.setOnAction(event -> gameController.startGame());
//        return startGameButton;
//    }
//
//    private Button stopGameButton() {
//        Button startGameButton = new Button("Stop");
//        startGameButton.setOnAction(event -> gameController.stopGame());
//        return startGameButton;
//    }

    private Button restartGameButton() {
        Button startGameButton = new Button("Restart");
        startGameButton.setOnAction(event -> gameController.restartGame());
        return startGameButton;
    }
}
