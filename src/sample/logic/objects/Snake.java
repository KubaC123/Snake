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
        body.add(new SnakeBody(GameController.SNAKE_INITIAL_X_COORDINATE - 20, GameController.SNAKE_INITIAL_Y_COORDINATE,
                GameObject.DEFAULT_WIDTH, GameObject.DEFAULT_HEIGHT, GameController.DEFAULT_DIRECTION));
        body.add(new SnakeBody(GameController.SNAKE_INITIAL_X_COORDINATE - 40, GameController.SNAKE_INITIAL_Y_COORDINATE,
                GameObject.DEFAULT_WIDTH, GameObject.DEFAULT_HEIGHT, GameController.DEFAULT_DIRECTION));
    }

    public void move(Direction direction) {
        head.move(direction);
        Direction prevBodyPartDirection = head.getPreviousDirection();
        for(int bodyNr = 0; bodyNr < body.size(); bodyNr++) {
            body.get(bodyNr).move(prevBodyPartDirection);
            prevBodyPartDirection = body.get(bodyNr).getPreviousDirection();
        }
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public GameObject getHead() {
        return head;
    }

    public List<SnakeBody> getBody() {
        return body;
    }

    public List<GameObject> getHeadAndBody() {
        List<GameObject> headAndBody = new ArrayList<>();
        headAndBody.add(head);
        headAndBody.addAll(body);
        return headAndBody;
    }
}
