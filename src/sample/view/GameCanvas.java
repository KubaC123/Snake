package sample.view;

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.paint.Color;
import sample.core.GameObject;
import sample.logic.GameController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class GameCanvas extends Canvas {

    private List<GameObject> gameObjects = new ArrayList<>();
    private int score = 0;

    public GameCanvas(double width, double height) {
        super(width, height);
    }

    public void setObjects(List<GameObject> gameObjects) {
        this.gameObjects = gameObjects;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void render() {
        GraphicsContext graphicsContext = getGraphicsContext2D();
        graphicsContext.clearRect(0, 0, getWidth(), getHeight());
        graphicsContext.setFill(Color.RED);
        gameObjects.stream()
                .collect(Collectors.groupingBy(GameObject::getType))
                .forEach((type, objects) -> {
                    graphicsContext.setFill(type.getColor());
                    objects.forEach(gameObject -> drawGameObject(gameObject, graphicsContext));
                });
        graphicsContext.setFill(Color.RED);
        graphicsContext.fillText("SCORE: " + score, 20 ,15);
    }

    private void drawGameObject(GameObject gameObject, GraphicsContext graphicsContext) {
        if(Objects.nonNull(gameObject.getImage())) {
            graphicsContext.drawImage(gameObject.getImage(), gameObject.getX(), gameObject.getY(), gameObject.getWidth(), gameObject.getHeight());
        } else {
            graphicsContext.fillRect(gameObject.getX(), gameObject.getY(), gameObject.getWidth(), gameObject.getHeight());
        }
    }

    public void youLostScreen() {
        GraphicsContext graphicsContext = getGraphicsContext2D();
        graphicsContext.clearRect(0, 0, getWidth(), getHeight());
        graphicsContext.setFill(Color.DARKRED);
        graphicsContext.fillRect(0, 0, GameController.GAME_CANVAS_WIDTH, GameController.GAME_CANVAS_HEIGHT);
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillText("GAME OVER. YOU SCORED: " + score, 210, 250);

    }
}
