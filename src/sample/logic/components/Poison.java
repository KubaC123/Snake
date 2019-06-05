package sample.logic.components;

import sample.core.GameObject;
import sample.logic.GameObjectType;

public class Poison extends GameObject {

    public Poison(double xCoordinate, double yCoordinate, double width, double height) {
        super(xCoordinate, yCoordinate, width, height, GameObjectType.POISON);
    }
}
