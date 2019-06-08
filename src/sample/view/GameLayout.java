package sample.view;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import sample.logic.GameController;

public class GameLayout extends BorderPane {

    GameController gameController;

    public GameLayout(GameCanvas gameCanvas, GameController gameController) {
        this.gameController = gameController;
        this.setCenter(gameCanvas);
        this.setRight(rightSideMenu());
    }

    private VBox rightSideMenu() {
        VBox rightSideMenuVBox = new VBox();
        rightSideMenuVBox.getChildren().addAll(startGameButton(), stopGameButton(), restartGameButton());
        return rightSideMenuVBox;
    }

    private Button startGameButton() {
        Button startGameButton = new Button("Start");
        startGameButton.setOnAction(event -> gameController.startGame());
        return startGameButton;
    }

    private Button stopGameButton() {
        Button startGameButton = new Button("Stop");
        startGameButton.setOnAction(event -> gameController.stopGame());
        return startGameButton;
    }

    private Button restartGameButton() {
        Button startGameButton = new Button("Restart");
        startGameButton.setOnAction(event -> gameController.restartGame());
        return startGameButton;
    }
}
