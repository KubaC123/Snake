package sample.logic.objects;

import sample.core.GameObject;
import sample.logic.GameObjectType;
import sample.view.Images;

public class Poison extends GameObject {

    public Poison(double xCoordinate, double yCoordinate, double width, double height) {
        super(xCoordinate, yCoordinate, width, height, GameObjectType.POISON, Images.POISON);
    }
}
