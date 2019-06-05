package sample.logic;

import sample.core.Direction;
import sample.core.GameObject;
import sample.logic.components.Snake;

import java.util.List;

public class GameController {

    private Snake snake;
    private List<GameObject> allGameObjects;

    public void moveSnake(Direction direction) {
        switch (direction) {
            case UP: {
                snake.updateHead(snake.getHead().getX(), snake.getHead().getY() - 1);
                // TODO collision detecion and resolve
            }

            case DOWN: {
                snake.updateHead(snake.getHead().getX(), snake.getHead().getY() + 1);
            }

            case LEFT: {
                snake.updateHead(snake.getHead().getX() - 1, snake.getHead().getY());

            }

            case RIGHT: {
                snake.updateHead(snake.getHead().getX() + 1, snake.getHead().getY());

            }
        }
    }

}
