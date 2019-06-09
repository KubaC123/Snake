package sample.logic.objects;

import sample.core.GameObject;
import sample.logic.GameObjectType;
import sample.view.Images;

public class Wall extends GameObject {

    public Wall(double xCoordinate, double yCoordinate, double width, double height) {
        super(xCoordinate, yCoordinate, width, height, GameObjectType.WALL, Images.WALL);
    }
}
