package sample.logic.objects;

import sample.core.GameObject;
import sample.logic.GameController;
import sample.logic.GameObjectType;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Snake {

    public static final double INITIAL_X_COORDINATE = 40.d;
    public static final double INITIAL_Y_COORDINATE = 50.d;

    private GameObject head;
    private LinkedList<GameObject> body;

    public Snake(double headX, double headY) {
        this.head = new GameObject(headX, headY, GameObject.DEFAULT_WIDTH, GameObject.DEFAULT_HEIGHT, GameObjectType.SNAKE_HEAD);
        this.body = new LinkedList<>();
    }

    public void moveUp() {
        updateHead(head.getX(), head.getY() - GameController.SPEED);
    }

    public void moveDown() {
        updateHead(head.getX(), head.getY() + GameController.SPEED);
    }

    public void moveLeft() {
        updateHead(head.getX() - GameController.SPEED, head.getY());
    }

    public void moveRight() {
        updateHead(head.getX() + GameController.SPEED, head.getY());
    }

    private synchronized void updateHead(double xCoordinate, double yCoordinate) {
        head.updateCoordinates(xCoordinate, yCoordinate);
    }

    private void updateBody() {
        for(int i = body.size() - 1; i >= 1; i--) {
            body.get(i).setX(body.get(i-1).getX());
            body.get(i).setY(body.get(i-1).getY());
        }
    }

    public GameObject getHead() {
        return head;
    }

    public List<GameObject> getBody() {
        return body;
    }

    public List<GameObject> getHeadAndBody() {
        List<GameObject> headAndBody = new ArrayList<>();
        headAndBody.add(head);
        headAndBody.addAll(body);
        return headAndBody;
    }

    public void feed() {
        double x, y;
        if(!body.isEmpty()) {
            x = body.getLast().getX() + 20;
            y = body.getLast().getY() + 20;
        } else {
            x = head.getX() + 20;
            y = head.getY() + 20;
        }
        GameObject g = new GameObject(x, y, 20, 20, GameObjectType.SNAKE_BODY);
        body.add(g);
        updateBody();
    }

    public void starve(Poison poison) {
        body.removeLast();
    }
}
