package sample.core;

import sample.logic.OnCollisionAction;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CollisionDetector {

    public static List<OnCollisionAction> detectCollisionsWithRoot(GameObject root, Collection<GameObject> gameObjects) {
        return gameObjects.stream()
                .filter(gameObject -> root.getBoundingBox().contains(gameObject.getX(), gameObject.getY()))
                .map(gameObject -> gameObject.getType().getOnCollisionAction())
                .collect(Collectors.toList());
    }
}
