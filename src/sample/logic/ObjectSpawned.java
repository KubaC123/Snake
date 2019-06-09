package sample.logic;

import javafx.geometry.Point2D;
import sample.core.GameObject;
import sample.logic.objects.*;

import java.util.List;
import java.util.Random;

class ObjectSpawned {

    static Random random = new Random();

    static Snake newSnake() {
        return new Snake();
    }

    static Food startFood() {
        return new Food(GameController.SNAKE_INITIAL_X_COORDINATE + 100,
                GameController.SNAKE_INITIAL_Y_COORDINATE + 100,
                GameObject.DEFAULT_WIDTH, GameObject.DEFAULT_HEIGHT,
                Food.foodImages.get(random.nextInt(Food.foodImages.size())));
    }

    static Food newFood(List<GameObject> allGameObjects) {
        Point2D point = getRandomSpawnPoint(allGameObjects);
        return new Food(point.getX(), point.getY(),
                GameObject.DEFAULT_WIDTH, GameObject.DEFAULT_HEIGHT,
                Food.foodImages.get(random.nextInt(Food.foodImages.size())));
    }

    static Poison newPoison(List<GameObject> allGameObjects) {
        Point2D point = getRandomSpawnPoint(allGameObjects);
        return new Poison(point.getX(), point.getY(),
                GameObject.DEFAULT_WIDTH, GameObject.DEFAULT_HEIGHT);
    }

    static Fire newFire(List<GameObject> allGameObjects) {
        Point2D point = getRandomSpawnPoint(allGameObjects);
        return new Fire(point.getX(), point.getY(),
                GameObject.DEFAULT_WIDTH, GameObject.DEFAULT_HEIGHT);
    }

    static Wall newWall(List<GameObject> allGameObjects) {
        Point2D point = getRandomSpawnPoint(allGameObjects);
        return new Wall(point.getX(), point.getY(),
                GameObject.DEFAULT_WIDTH, GameObject.DEFAULT_HEIGHT);
    }

    private static Point2D getRandomSpawnPoint(List<GameObject> allGameObjects) {
        double xCoordinate = 0.d;
        double yCoordinate = 0.d;
        boolean found = false;
        while(!found) {
            xCoordinate = random.nextInt((int)GameController.GAME_CANVAS_WIDTH/(int) GameObject.DEFAULT_WIDTH);
            yCoordinate = random.nextInt((int)GameController.GAME_CANVAS_HEIGHT/(int)GameObject.DEFAULT_HEIGHT);
            for(GameObject gameObject : allGameObjects) {
                if(gameObject.getX() == xCoordinate && gameObject.getY() == yCoordinate);
            }
            found = true;
        }
        return new Point2D(xCoordinate*GameObject.DEFAULT_WIDTH, yCoordinate*GameObject.DEFAULT_HEIGHT);
    }
}
