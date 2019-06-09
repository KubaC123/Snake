package sample.logic.objects;

import sample.core.Direction;
import sample.core.GameObject;
import sample.logic.GameController;

import java.util.ArrayList;
import java.util.List;

public class Snake {

    private SnakeHead head;
    private ArrayList<SnakeBody> body;

    public Snake() {
        this.head = new SnakeHead(GameController.SNAKE_INITIAL_X_COORDINATE, GameController.SNAKE_INITIAL_Y_COORDINATE,
                GameObject.DEFAULT_WIDTH, GameObject.DEFAULT_HEIGHT, GameController.DEFAULT_DIRECTION);
        this.body = new ArrayList<>();
        body.add(new SnakeBody(GameController.SNAKE_INITIAL_X_COORDINATE - GameObject.DEFAULT_HEIGHT,
                GameController.SNAKE_INITIAL_Y_COORDINATE, GameObject.DEFAULT_WIDTH, GameObject.DEFAULT_HEIGHT, GameController.DEFAULT_DIRECTION));
        body.add(new SnakeBody(GameController.SNAKE_INITIAL_X_COORDINATE - 2*GameObject.DEFAULT_HEIGHT,
                GameController.SNAKE_INITIAL_Y_COORDINATE, GameObject.DEFAULT_WIDTH, GameObject.DEFAULT_HEIGHT, GameController.DEFAULT_DIRECTION));
    }

    public void move(Direction direction) {
        head.move(direction);
        Direction prevBodyPartDirection = head.getPreviousDirection();
        for(int bodyNr = 0; bodyNr < body.size(); bodyNr++) {
            body.get(bodyNr).move(prevBodyPartDirection);
            prevBodyPartDirection = body.get(bodyNr).getPreviousDirection();
        }
        try {
            Thread.sleep(100);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addBodyPart() {
        int bodySize = body.size();
        double xStart = body.get(bodySize - 1).getX();
        double yStart = body.get(bodySize - 1).getY();
        Direction lastBodyPartDirection = body.get(bodySize - 1).getCurrentDirection();
        switch(lastBodyPartDirection) {
            case UP: {
                yStart += GameObject.DEFAULT_HEIGHT;
                break;
            }
            case DOWN: {
                yStart -= GameObject.DEFAULT_HEIGHT;
                break;
            }
            case LEFT: {
                xStart += GameObject.DEFAULT_HEIGHT;
                break;
            }
            case RIGHT: {
                xStart -= GameObject.DEFAULT_HEIGHT;
                break;
            }
        }
        body.add(new SnakeBody(xStart, yStart, GameObject.DEFAULT_WIDTH, GameObject.DEFAULT_HEIGHT, lastBodyPartDirection));
    }

    public void removeLastBodyPart() {
        int bodySize = body.size();
        body.remove(bodySize - 1);
    }

    public GameObject getHead() {
        return head;
    }

    public List<GameObject> getHeadAndBody() {
        List<GameObject> headAndBody = new ArrayList<>();
        headAndBody.add(head);
        headAndBody.addAll(body);
        return headAndBody;
    }
}
