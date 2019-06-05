package sample.core;

import javafx.geometry.BoundingBox;
import javafx.scene.shape.Rectangle;
import sample.logic.GameObjectType;

import java.time.Year;

public class GameObject extends Rectangle {

    private GameObjectType type;
    private BoundingBox boundingBox;

    public GameObject(double xCoordinate, double yCoordinate, double width, double height, GameObjectType type) {
        this.setX(xCoordinate);
        this.setY(yCoordinate);
        this.setWidth(width);
        this.setHeight(height);
        this.type = type;
        this.boundingBox = new BoundingBox(xCoordinate, yCoordinate, width, height);
    }

    public void updateCoordinates(double xCoordinate, double yCoordinate) {
        this.setX(xCoordinate);
        this.setY(yCoordinate);
        updateBounds();
    }

    private void updateBounds() {
        boundingBox = new BoundingBox(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }

    public GameObjectType getType() {
        return type;
    }

    public BoundingBox getBoundingBox() {
        return boundingBox;
    }
}
