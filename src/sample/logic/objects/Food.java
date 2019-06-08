package sample.logic.objects;

import sample.core.GameObject;
import sample.logic.GameObjectType;

public class Food extends GameObject {

    public Food(double xCoordinate, double yCoordinate, double width, double height) {
        super(xCoordinate, yCoordinate, width, height, GameObjectType.FOOD);
    }
}
