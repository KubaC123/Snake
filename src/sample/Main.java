package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.core.CollisionDetector;
import sample.core.GameObject;
import sample.logic.GameObjectType;
import sample.logic.OnCollisionAction;
import sample.view.GameCanvas;

import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        GameCanvas gameCanvas = new GameCanvas(400.d, 400.d);

        GameObject snakeHead = new GameObject(30, 20, 10, 10, GameObjectType.SNAKE_HEAD);
        gameCanvas.addGameObject(snakeHead);
        gameCanvas.addGameObject(new GameObject(40, 20, 10, 10, GameObjectType.FOOD));
        gameCanvas.addGameObject(new GameObject(34, 24, 10, 10, GameObjectType.POISON));
        VBox root = new VBox();
        root.getChildren().add(gameCanvas);

        new AnimationTimer() {
            long lastTick = 0;
            public void handle(long now) {
                if (lastTick == 0) {
                    lastTick = now;
                    gameCanvas.render();
                    List<OnCollisionAction> actions = CollisionDetector.detectCollisionsWithRoot(snakeHead, gameCanvas.getGameObjects());
                    return;
                }

                if (now - lastTick > 1000000000 / 5) {
                    lastTick = now;
                    gameCanvas.render();
                }
            }
        }.start();

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
