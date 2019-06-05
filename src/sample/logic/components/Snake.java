package sample.logic.components;

import sample.core.GameObject;
import sample.logic.GameObjectType;

import java.util.LinkedList;
import java.util.List;

public class Snake {

    private GameObject head;
    private LinkedList<GameObject> body;

    public Snake(double headX, double headY) {
        this.head = new GameObject(headX, headY, 20, 20, GameObjectType.SNAKE_HEAD);
        this.body = new LinkedList<>();
    }

    public void updateHead(double xCoordinate, double yCoordinate) {
        head.setX(xCoordinate);
        head.setY(yCoordinate);
    }

    public void updateBody() {
        for(int i = body.size() - 1; i > 0; i--) {
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

    public void feed(Food food) {
        body.add(food);
    }

    public void starve(Poison poison) {
        body.removeLast();
    }
}
