package sample.logic;

import javafx.concurrent.Task;
import sample.core.CollisionDetector;
import sample.core.Direction;
import sample.core.GameObject;
import sample.logic.objects.Food;
import sample.logic.objects.Snake;
import sample.logic.objects.Wall;
import sample.view.GameCanvas;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

public class GameController {

    public static double SPEED = 5.d;
    public static double GAME_CANVAS_WIDTH = 500.d;
    public static double GAME_CANVAS_HEIGHT = 500.d;
    public static double MAIN_STAGE_WIDTH = 600.d;
    public static double MAIN_STAGE_HEIGHT = 600.d;

    private Direction currentDirection;
    private boolean gameRunning = true;

    private Snake snake;
    private AtomicReference<Food> food;
    private Random random;

    private GameCanvas gameCanvas;

    public GameController(GameCanvas gameCanvas) {
        this.currentDirection = Direction.RIGHT;
        random = new Random();
        this.gameCanvas = gameCanvas;
        snake = new Snake(Snake.INITIAL_X_COORDINATE, Snake.INITIAL_Y_COORDINATE);
        snake.feed();
        snake.feed();
        snake.feed();

        snake.getHeadAndBody().forEach(object -> System.out.println("x: " + object.getX()  +  ", y:" + object.getY()));

        food = new AtomicReference<>(new Food(120, 120, 20, 20));
    }

    private ArrayList<Wall> getRoomWalls() {
        double wallSize = 5.d;
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
        snake = new Snake(Snake.INITIAL_X_COORDINATE, Snake.INITIAL_Y_COORDINATE);
        food.set(new Food(120, 120, 20, 20));
        this.currentDirection = Direction.RIGHT;
        gameRunning = true;
    }

    public void setCurrentDirection(Direction direction) {
        currentDirection = direction;
    }

    private synchronized  List<GameObject> getAllCurrentGameObjects() {
        List<GameObject> allGameObjects = new ArrayList<>();
        allGameObjects.addAll(snake.getHeadAndBody());
        allGameObjects.add(food.get());
        allGameObjects.addAll(getRoomWalls());
        return allGameObjects;
    }

    private void startSnakeMovementThread() {
        Thread snakeMovementThread = new Thread(new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                while(gameRunning && !isCancelled()) {
                    switch (currentDirection) {
                        case UP: {
                            snake.moveUp();
                            resolveCollisions(CollisionDetector.detectCollisionsWithRoot(snake.getHead(), getAllCurrentGameObjects()));
                            break;
                        }
                        case DOWN: {
                            snake.moveDown();
                            resolveCollisions(CollisionDetector.detectCollisionsWithRoot(snake.getHead(), getAllCurrentGameObjects()));
                            break;
                        }
                        case LEFT: {
                            snake.moveLeft();
                            resolveCollisions(CollisionDetector.detectCollisionsWithRoot(snake.getHead(), getAllCurrentGameObjects()));
                            break;
                        }
                        case RIGHT: {
                            snake.moveRight();
                            resolveCollisions(CollisionDetector.detectCollisionsWithRoot(snake.getHead(), getAllCurrentGameObjects()));
                            break;
                        }
                    }
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
        snakeMovementThread.setDaemon(true);
        snakeMovementThread.start();
    }

    private void resolveCollisions(List<OnCollisionAction> collisionActions) {
        checkCollisionWithWalls(collisionActions);
        collisionActions.forEach(this::resolveCollision);
    }

    private void checkCollisionWithWalls(List<OnCollisionAction> collisionActions) {
        if((snake.getHead().getY() < 0) || (snake.getHead().getY() > gameCanvas.getHeight()) || (snake.getHead().getX() < 0) || (snake.getHead().getX() > gameCanvas.getWidth())) {
            collisionActions.add(OnCollisionAction.DEAD);
        }
    }

    private void resolveCollision(OnCollisionAction onCollisionAction) {
        switch (onCollisionAction) {
            case DEAD: {
                stopGame();
                System.out.println("YOU LOST !!!!!");
                break;
            }
            case GAIN: {
                System.out.println("HERE");
                snake.feed();
                System.out.println("FOOD EATEN");
                newFood();
                printObj();
                break;
            }
            case LOSS: {
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
        double foodXCoordinate = 0.d;
        double foodYCoordinate = 0.d;
        boolean found = false;
        while(!found) {
            foodXCoordinate = random.nextInt((int)gameCanvas.getWidth());
            foodYCoordinate = random.nextInt((int)gameCanvas.getHeight());

            for(GameObject gameObject : snake.getHeadAndBody()) {
                if(gameObject.getX() == foodXCoordinate && gameObject.getY() == foodYCoordinate);
            }
            found = true;
        }
        food.set(new Food(foodXCoordinate, foodYCoordinate, 20 ,20));
        System.out.println(food.get().getX());System.out.println(food.get().getY());
    }

    private void startGameCanvasRenderThread() {
        Thread gameCanvasRenderThread = new Thread(new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                while(gameRunning && !isCancelled()) {
                    printObj();
                    printSnake();
                    gameCanvas.setObj(getAllCurrentGameObjects());
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

    private void printSnake() {
        System.out.println("SNAKE");
        snake.getHeadAndBody().forEach(object -> System.out.println("x: " + object.getX()  +  ", y:" + object.getY()));
    }

    private void printObj() {
        System.out.println("ALL");
        getAllCurrentGameObjects().forEach(object -> System.out.println("x: " + object.getX()  +  ", y:" + object.getY()));
    }
}