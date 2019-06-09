package sample.logic.objects;

import sample.core.Direction;
import sample.core.MovableGameObject;
import sample.logic.GameObjectType;
import sample.view.Images;

class SnakeBody extends MovableGameObject {

    SnakeBody(double xCoordinate, double yCoordinate, double width, double height, Direction direction) {
        super(xCoordinate, yCoordinate, width, height, GameObjectType.SNAKE_BODY, Images.LEGO);
        setCurrentDirection(direction);
    }
}
