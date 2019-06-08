package sample.core;

import javafx.scene.shape.Rectangle;
import sample.logic.GameObjectType;

public class GameObject extends Rectangle {

    public static final double DEFAULT_WIDTH = 20.d;
    public static final double DEFAULT_HEIGHT = 20.d;

    private GameObjectType type;

    public GameObject(double xCoordinate, double yCoordinate, double width, double height, GameObjectType type) {
        this.setX(xCoordinate);
        this.setY(yCoordinate);
        this.setWidth(width);
        this.setHeight(height);
        this.type = type;
    }

    public void updateCoordinates(double xCoordinate, double yCoordinate) {
        this.setX(xCoordinate);
        this.setY(yCoordinate);
    }

    public GameObjectType getType() {
        return type;
    }
}
