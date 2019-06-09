package sample.logic;

import javafx.concurrent.Task;
import javafx.geometry.Point2D;
import sample.core.CollisionDetector;
import sample.core.Direction;
import sample.core.GameObject;
import sample.logic.objects.Food;
import sample.logic.objects.Poison;
import sample.logic.objects.Snake;
import sample.logic.objects.Wall;
import sample.view.GameCanvas;
import sample.view.Images;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class GameController {

    public static double SPEED = 20.d;
    public static double GAME_CANVAS_WIDTH = 600.d;
    public static double GAME_CANVAS_HEIGHT = 600.d;
    public static double MAIN_STAGE_WIDTH = 900.d;
    public static double MAIN_STAGE_HEIGHT = 900.d;
    public static final double SNAKE_INITIAL_X_COORDINATE = 100.d;
    public static final double SNAKE_INITIAL_Y_COORDINATE = 100.d;
    public static Direction DEFAULT_DIRECTION = Direction.RIGHT;

    private Direction currentDirection;
    private boolean gameRunning = true;
    private boolean restarted = false;

    private Snake snake;
    private AtomicReference<Food> food;
    private AtomicReference<Poison> poison;
    private double wallSize = 20.d;

    private AtomicInteger score = new AtomicInteger(0);

    private Random random;
    private ArrayList<Images> foodImages = new ArrayList<>();
    private GameCanvas gameCanvas;

    public GameController(GameCanvas gameCanvas) {
        this.currentDirection = Direction.RIGHT;
        random = new Random();
        this.gameCanvas = gameCanvas;
        snake = new Snake();
        foodImages.add(Images.APPLE);
        foodImages.add(Images.WATERMELLON);
        foodImages.add(Images.BERRY);
        foodImages.add(Images.COLA);
        foodImages.add(Images.COLAZERO);
        food = new AtomicReference<>(new Food(120, 120,
                GameObject.DEFAULT_WIDTH, GameObject.DEFAULT_HEIGHT, foodImages.get(random.nextInt(foodImages.size()))));
        poison = new AtomicReference<>(null);
        startGame();
    }

    private ArrayList<Wall> getRoomWalls() {
        ArrayList<Wall> walls = new ArrayList<>();
        walls.add(new Wall(0, 0, gameCanvas.getWidth(), wallSize));
        walls.add(new Wall(0, 0, wallSize, gameCanvas.getHeight()));
        walls.add(new Wall(0, gameCanvas.getWidth()-wallSize, gameCanvas.getWidth(), wallSize));
        walls.add(new Wall(gameCanvas.getHeight()-wallSize, 0, wallSize, gameCanvas.getHeight()));
        return walls;
    }

    public void startGame() {
        gameRunning = true;
        startGameCanvasRenderThread();
        startSnakeMovementThread();
    }

    public void stopGame() {
        gameRunning = false;
    }

    public void restartGame() {
        gameRunning = false;
        resetGameState();
        gameRunning = true;
        startGame();
    }

    private void resetGameState() {
        score.set(0);
        snake = new Snake();
        food.set(new Food(120, 120,
                GameObject.DEFAULT_WIDTH, GameObject.DEFAULT_HEIGHT, foodImages.get(random.nextInt(foodImages.size()))));
        this.currentDirection = Direction.RIGHT;
    }

    public void setCurrentDirection(Direction direction) {
        currentDirection = direction;
    }

    private synchronized  List<GameObject> getAllCurrentGameObjects() {
        List<GameObject> allGameObjects = new ArrayList<>();
        allGameObjects.addAll(snake.getHeadAndBody());
        allGameObjects.add(food.get());
        allGameObjects.addAll(getRoomWalls());
        Optional.ofNullable(poison.get()).ifPresent(allGameObjects::add);
        return allGameObjects;
    }

    private void startSnakeMovementThread() {
        Thread snakeMovementThread = new Thread(new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                while(gameRunning && !isCancelled()) {
                    snake.move(currentDirection);
                    resolveCollisions(CollisionDetector.detectCollisionsWithRoot(snake.getHead(), getAllCurrentGameObjects()));
                }
                return null;
            }
        });
        snakeMovementThread.setDaemon(true);
        snakeMovementThread.start();
    }

    private void resolveCollisions(List<OnCollisionAction> collisionActions) {
        checkCollisionWithWalls(collisionActions);
        collisionActions.forEach(this::resolveCollision);
    }

    private void checkCollisionWithWalls(List<OnCollisionAction> collisionActions) {
        if((snake.getHead().getY() < wallSize) || (snake.getHead().getY() > gameCanvas.getHeight() - wallSize) || (snake.getHead().getX() < wallSize) || (snake.getHead().getX() > gameCanvas.getWidth() - wallSize)) {
            collisionActions.add(OnCollisionAction.DEAD);
        }
    }

    private void resolveCollision(OnCollisionAction onCollisionAction) {
        switch (onCollisionAction) {
            case DEAD: {
                stopGame();
                gameCanvas.youLostScreen();
                break;
            }
            case GAIN: {
                poison.set(null);
                score.set(score.intValue() + 20);
                for(int i=0; i<1; i++) {
                    snake.addBodyPart();
                }
                newFood();
                break;
            }
            case LOSS: {
                poison.set(null);
                snake.removeLastBodyPart();
                score.set(score.intValue() - 20);
                break;
            }
            case NONE: {
                break;
            }
            case DOUBLE_GAIN: {
                break;
            }
            case DOUBLE_LOSS: {
                break;
            }
            default: {
                break;
            }
        }
    }

    private void newFood() {
        Point2D point = findFreePoint();
        food.set(new Food(point.getX(), point.getY(), GameObject.DEFAULT_WIDTH,
                GameObject.DEFAULT_HEIGHT, foodImages.get(random.nextInt(foodImages.size()))));
        if(random.nextInt(2) == 1) {
            newPoison();
        }
    }

    private Point2D findFreePoint() {
        double xCoordinate = 0.d;
        double yCoordinate = 0.d;
        boolean found = false;
        while(!found) {
            xCoordinate = random.nextInt((int)gameCanvas.getWidth() - 80);
            yCoordinate = random.nextInt((int)gameCanvas.getHeight() - 80);

            for(GameObject gameObject : getAllCurrentGameObjects()) {
                if(gameObject.getX() == xCoordinate && gameObject.getY() == yCoordinate);
            }
            found = true;
        }
        return new Point2D(xCoordinate, yCoordinate);
    }

    private void newPoison() {
        Point2D point = findFreePoint();
        poison.set(new Poison(point.getX(), point.getY(),
                GameObject.DEFAULT_WIDTH, GameObject.DEFAULT_HEIGHT));
    }


    private void startGameCanvasRenderThread() {
        Thread gameCanvasRenderThread = new Thread(new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                while(gameRunning && !isCancelled()) {
                    gameCanvas.setObjects(getAllCurrentGameObjects());
                    gameCanvas.setScore(score.get());
                    gameCanvas.render();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException exception) {
                        if(isCancelled()) {
                            break;
                        }
                    }
                }
                return null;
            }
        });
        gameCanvasRenderThread.setDaemon(true);
        gameCanvasRenderThread.start();
    }
}
