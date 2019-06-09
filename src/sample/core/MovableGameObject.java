package sample.core;

import sample.logic.GameController;
import sample.logic.GameObjectType;
import sample.view.Images;

public class MovableGameObject extends GameObject {

    private Direction currentDirection;
    private Direction previousDirection;

    public MovableGameObject(double xCoordinate, double yCoordinate, double width, double height, GameObjectType type) {
        super(xCoordinate, yCoordinate, width, height, type);
    }

    public MovableGameObject(double xCoordinate, double yCoordinate, double width, double height, GameObjectType type, Images image) {
        super(xCoordinate, yCoordinate, width, height, type, image);
    }

    public void setPreviousDirection(Direction direction) {
        previousDirection = direction;
    }

    public Direction getPreviousDirection() {
        return previousDirection;
    }

    public void setCurrentDirection(Direction direction) {
        currentDirection = direction;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public void move(Direction newDirection) {
        previousDirection = currentDirection;
        currentDirection = newDirection;
        switch(currentDirection) {
            case UP: {
                updateCoordinates(getX(), getY() - GameController.SPEED);
                if(getY() < 0) {
                    updateCoordinates(getX(), GameController.GAME_CANVAS_HEIGHT - 1);
                }
                break;
            }
            case DOWN: {
                updateCoordinates(getX(), getY() + GameController.SPEED);
                if(getY() >= GameController.GAME_CANVAS_HEIGHT) {
                    updateCoordinates(getX(), 0);
                }
                break;
            }
            case LEFT: {
                updateCoordinates(getX() - GameController.SPEED, getY());
                if(getX() < 0) {
                    updateCoordinates(GameController.GAME_CANVAS_WIDTH - 1, getY());
                }
                break;
            }
            case RIGHT: {
                updateCoordinates(getX() + GameController.SPEED, getY());
                if(getX() >= GameController.GAME_CANVAS_WIDTH) {
                    updateCoordinates(0, getY());
                }
                break;
            }
        }
    }
}
