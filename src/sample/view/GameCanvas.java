package sample.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import sample.core.GameObject;
import sample.logic.GameObjectType;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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
        GraphicsContext graphicsContext = getGraphicsContext2D();
        graphicsContext.clearRect(0, 0, getWidth(), getHeight());
        System.out.println("RENDER");
        gameObjects.stream()
                .collect(Collectors.groupingBy(GameObject::getType))
                .forEach((type, objects) -> {
                    graphicsContext.setFill(type.getColor());
                    objects.forEach(gameObject -> drawGameObject(gameObject, graphicsContext));
                });
    }

    private void drawGameObject(GameObject gameObject, GraphicsContext graphicsContext) {
        if(Objects.nonNull(gameObject.getImage())) {
            graphicsContext.drawImage(gameObject.getImage(), gameObject.getX(), gameObject.getY(), gameObject.getWidth(), gameObject.getHeight());
        } else {
            graphicsContext.fillRect(gameObject.getX(), gameObject.getY(), gameObject.getWidth(), gameObject.getHeight());
        }
    }
}
