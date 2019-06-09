package sample.core;

import sample.logic.OnCollisionAction;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CollisionDetector {

    public static List<OnCollisionAction> detectCollisionsWithRoot(GameObject root, Collection<GameObject> gameObjects) {
        return gameObjects.stream()
                .filter(gameObject -> collisionDetected(root, gameObject))
                .map(gameObject -> gameObject.getType().getOnCollisionAction())
                .collect(Collectors.toList());
    }

    private static boolean collisionDetected(GameObject root, GameObject gameObject) {
        double dx = root.getX() - gameObject.getX();
        double dy = root.getY() - gameObject.getY();
        double distance = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
        return distance < gameObject.getWidth() || distance < gameObject.getHeight();
    }

}
