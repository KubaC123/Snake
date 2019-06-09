package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.core.Direction;
import sample.logic.GameController;
import sample.view.GameCanvas;
import sample.view.GameLayout;

public class Main extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception {
        GameCanvas gameCanvas = new GameCanvas(GameController.GAME_CANVAS_WIDTH, GameController.GAME_CANVAS_HEIGHT);
        GameController gameController = new GameController(gameCanvas);
        GameLayout gameLayout = new GameLayout(gameCanvas, gameController);
        primaryStage.setTitle("TRUMP SNAKE XD");
        Scene scene = new Scene(gameLayout, GameController.MAIN_STAGE_WIDTH, gameController.MAIN_STAGE_HEIGHT);
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
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
