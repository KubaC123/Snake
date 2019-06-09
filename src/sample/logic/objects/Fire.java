package sample.logic.objects;

import sample.core.GameObject;
import sample.logic.GameObjectType;
import sample.view.Images;

public class Fire extends GameObject {

    public Fire(double xCoordinate, double yCoordinate, double width, double height) {
        super(xCoordinate, yCoordinate, width, height, GameObjectType.FIRE, Images.FIRE);
    }
}
