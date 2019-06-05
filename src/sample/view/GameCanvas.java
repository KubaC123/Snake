package sample.view;

import javafx.scene.canvas.Canvas;
import sample.core.GameObject;

import java.util.Collection;
import java.util.stream.Collectors;

public class GameCanvas extends Canvas {

    public GameCanvas(double width, double height) {
        super(width, height);
    }

    public void render(Collection<GameObject> gameObjects) {
        gameObjects.stream()
                .collect(Collectors.groupingBy(GameObject::getType))
                .forEach((type, objects) -> {
                    getGraphicsContext2D().setFill(type.getColor());
                    objects.forEach(gameObject -> getGraphicsContext2D().fillRect(gameObject.getX(), gameObject.getY(), gameObject.getWidth(), gameObject.getHeight()));
                });
    }
}
