package sample.logic.components;

import sample.core.GameObject;
import sample.logic.GameObjectType;

public class Wall extends GameObject {

    public Wall(double xCoordinate, double yCoordinate, double width, double height, GameObjectType gameObjectType) {
        super(xCoordinate, yCoordinate, width, height, gameObjectType);
    }
}
