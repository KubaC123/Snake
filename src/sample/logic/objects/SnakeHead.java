package sample.logic.objects;

import sample.core.Direction;
import sample.core.GameObject;
import sample.core.Movable;
import sample.logic.GameController;
import sample.logic.GameObjectType;

public class SnakeHead extends GameObject implements Movable {

    private Direction currentDirection;
    private Direction previousDirection;

    public SnakeHead(double xCoordinate, double yCoordinate, double width, double height, Direction direction) {
        super(xCoordinate, yCoordinate, width, height, GameObjectType.SNAKE_HEAD);
        this.currentDirection = direction;
    }

    @Override
    public void move(Direction newDirection) {
        previousDirection = currentDirection;
        currentDirection = newDirection;
        switch(currentDirection) {
            case UP: {
                System.out.println("HEAD MOVE UP");
                updateCoordinates(getX(), getY() - GameController.SPEED);
                break;
            }
            case DOWN: {
                System.out.println("HEAD MOVE DOWN");
                updateCoordinates(getX(), getY() + GameController.SPEED);
                break;
            }
            case LEFT: {
                System.out.println("HEAD MOVE LEFT");
                updateCoordinates(getX() - GameController.SPEED, getY());
                break;
            }
            case RIGHT: {
                System.out.println("HEAD MOVE RIGHT");
                updateCoordinates(getX() + GameController.SPEED, getY());
                break;
            }
        }
    }

    public void setPreviousDirection(Direction previousDirection) {
        this.previousDirection = previousDirection;
    }

    public Direction getPreviousDirection() {
        return previousDirection;
    }

    public void setCurrentDirection(Direction currentDirection) {
        this.currentDirection = currentDirection;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }
}
