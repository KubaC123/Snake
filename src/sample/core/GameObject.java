package sample.core;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import sample.logic.GameObjectType;
import sample.view.Images;

import java.io.File;

public class GameObject extends Rectangle {

    public static final double DEFAULT_WIDTH = 20.d;
    public static final double DEFAULT_HEIGHT = 20.d;

    private GameObjectType type;
    private Image image;

    public GameObject(double xCoordinate, double yCoordinate, double width, double height, GameObjectType type) {
        this.setX(xCoordinate);
        this.setY(yCoordinate);
        this.setWidth(width);
        this.setHeight(height);
        this.type = type;
        this.image = null;
    }

    public GameObject(double xCoordinate, double yCoordinate, double width, double height, GameObjectType type, Images image) {
        this.setX(xCoordinate);
        this.setY(yCoordinate);
        this.setWidth(width);
        this.setHeight(height);
        this.type = type;
        setImage(image);
    }

    public void updateCoordinates(double xCoordinate, double yCoordinate) {
        this.setX(xCoordinate);
        this.setY(yCoordinate);
    }

    public void setImage(Images image) {
        String imagePath = System.getProperty("user.dir") + "/foodImages/" + image.getFileName();
        try {
            this.image = new Image(new File(imagePath).toURI().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Image getImage() {
        return image;
    }

    public GameObjectType getType() {
        return type;
    }
}
