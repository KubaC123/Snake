package sample.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import sample.core.GameObject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GameCanvas extends Canvas {

    private List<GameObject> gameObjects = new ArrayList<>();

    public GameCanvas(double width, double height) {
        super(width, height);
    }

    public void setObj(List<GameObject> gameObjects) {
        this.gameObjects =gameObjects;
    }

    public void render() {
        getGraphicsContext2D().clearRect(0, 0, getWidth(), getHeight());
        getGraphicsContext2D().setFill(Color.BLACK);
        gameObjects.stream()
                .collect(Collectors.groupingBy(GameObject::getType))
                .forEach((type, objects) -> {
                    getGraphicsContext2D().setFill(type.getColor());
                    objects.forEach(gameObject -> getGraphicsContext2D().fillRect(gameObject.getX(), gameObject.getY(), gameObject.getWidth(), gameObject.getHeight()));
                });
    }
}
