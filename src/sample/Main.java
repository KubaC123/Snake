package sample;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.core.Direction;
import sample.logic.GameController;
import sample.view.GameCanvas;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        GameCanvas gameCanvas = new GameCanvas(GameController.GAME_CANVAS_WIDTH, GameController.GAME_CANVAS_HEIGHT);
        GameController gameController = new GameController(gameCanvas);
        VBox vBox = new VBox(gameCanvas);
        vBox.setAlignment(Pos.CENTER);
        primaryStage.setTitle("SNAKE");
        Scene scene = new Scene(vBox, GameController.MAIN_STAGE_WIDTH, GameController.MAIN_STAGE_HEIGHT);
        scene.setOnKeyPressed(key ->  {
            switch (key.getCode()) {
                case UP: {
                    gameController.setCurrentDirection(Direction.UP);
                    break;
                }
                case DOWN: {
                    gameController.setCurrentDirection(Direction.DOWN);
                    break;
                }
                case LEFT: {
                    gameController.setCurrentDirection(Direction.LEFT);
                    break;
                }
                case RIGHT: {
                    gameController.setCurrentDirection(Direction.RIGHT);
                    break;
                }
            }
        });
        scene.setFill(Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
