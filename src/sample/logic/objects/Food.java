package sample.logic.objects;

import sample.core.GameObject;
import sample.logic.GameObjectType;
import sample.view.Images;

public class Food extends GameObject {

    public Food(double xCoordinate, double yCoordinate, double width, double height, Images image) {
        super(xCoordinate, yCoordinate, width, height, GameObjectType.FOOD, image);
    }
}
